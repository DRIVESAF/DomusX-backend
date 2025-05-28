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
public class DeviceServiceImpl  extends BaseServiceImpl<DeviceDao, Device> implements DeviceService{
    private final MessageChannel mqttOutboundChannel;
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
        //构建JSON命令
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", deviceId);
        map.put("command", command);
        String payload = JSON.toJSONString(map);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("mqtt_topic", "iot/device/control")
                .build();
        mqttOutboundChannel.send(message);
    }
    //处理状态上报
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleStatusMessage(Message<?> message) {
        String payload = message.getPayload().toString();
        try {
            JSONObject json = JSON.parseObject(payload);
            String deviceId = json.getString("device_id");
            Boolean status = json.getBoolean("status");
            //更新数据库状态
            UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("device_id", deviceId)
                    .set("status", status);
            baseMapper.update(null, updateWrapper);
            log.info("设备状态更新: {} -> {}", deviceId, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
