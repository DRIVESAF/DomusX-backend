package top.gx.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import top.gx.convert.DeviceConvert;
import top.gx.dao.DeviceDao;
import top.gx.entity.Device;
import top.gx.framework.common.constant.Constant;
import top.gx.framework.common.exception.ServerException;
import top.gx.framework.common.utils.PageResult;
import top.gx.framework.mybatis.service.impl.BaseServiceImpl;
import top.gx.query.DeviceQuery;
import top.gx.service.DeviceDataService;
import top.gx.service.DeviceService;
import top.gx.vo.DeviceVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Service
@Slf4j
@AllArgsConstructor
public class DeviceServiceImpl extends BaseServiceImpl<DeviceDao, Device> implements DeviceService {
    private final MessageChannel mqttOutboundChannel;
    private final DeviceDataService deviceDataService;

    @Override
    public PageResult<DeviceVO> page(DeviceQuery query) {
        Map<String, Object> params = getParams(query);
        IPage<Device> page = getPage(query);
        params.put(Constant.PAGE, page);
        List<Device> list = baseMapper.getList(params);
        return new PageResult<>(DeviceConvert.INSTANCE.convertList(list),
                page.getTotal());
    }

    private Map<String, Object> getParams(DeviceQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", query.getName());
        params.put("type", query.getType());
        params.put("status", query.getStatus());
        return params;
    }

    @Override
    public void sendCommand(String deviceId, String command) {
        QueryWrapper<Device> query = new QueryWrapper<>();
        query.eq("device_id", deviceId);
        Device device = this.getOne(query);
        if (device == null) {
            throw new ServerException("设备不存在");
        }
        // 构建JSON命令
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", deviceId);
        map.put("command", command);
        String payload = JSON.toJSONString(map);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("mqtt_topic", "iot/device/control")
                .build();
        mqttOutboundChannel.send(message);
    }

    // 处理状态上报
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleStatusMessage(Message<?> message) {
        String payload = message.getPayload().toString();
        log.info("收到MQTT消息: {}", payload);
        try {
            JSONObject json = JSON.parseObject(payload);
            String deviceId = json.getString("device_id");
            String status = json.getString("status");

            log.info("解析设备ID: {}, 状态: {}", deviceId, status);

            // 更新数据库状态
            UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("device_id", deviceId)
                    .set("status", "on".equals(status));
            Device device = baseMapper.update(null, updateWrapper) > 0
                    ? getOne(new QueryWrapper<Device>().eq("device_id", deviceId))
                    : null;

            if (device == null) {
                log.error("设备不存在: {}", deviceId);
                return;
            }

            log.info("设备状态更新成功: {} -> {}", deviceId, status);

            // 自动保存所有设备数据
            for (String key : json.keySet()) {
                // 跳过device_id和status字段，因为这些是设备基本信息
                if ("device_id".equals(key) || "status".equals(key)) {
                    continue;
                }

                String value = json.getString(key);
                String unit = getUnitForKey(key); // 根据key获取对应的单位
                log.info("保存设备数据: deviceId={}, key={}, value={}, unit={}",
                        device.getId(), key, value, unit);
                deviceDataService.saveDeviceData(device.getId(), key, value, unit);
            }

            log.info("设备数据保存完成: {}", deviceId);
        } catch (Exception e) {
            log.error("处理设备数据失败: {}", payload, e);
        }
    }

    /**
     * 根据数据key获取对应的单位
     */
    private String getUnitForKey(String key) {
        // 可以根据实际需求配置不同key对应的单位
        Map<String, String> unitMap = new HashMap<>();
        unitMap.put("temperature", "℃");
        unitMap.put("humidity", "%");
        unitMap.put("brightness", "lux");
        unitMap.put("power", "W");
        unitMap.put("voltage", "V");
        unitMap.put("current", "A");
        // 可以继续添加其他key的单位映射

        return unitMap.getOrDefault(key, "");
    }
}
