package top.gx.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import top.gx.entity.Device;
import top.gx.framework.common.exception.ServerException;
import top.gx.framework.common.utils.Result;
import top.gx.service.DeviceService;
import top.gx.service.SmartLockService;

import java.util.HashMap;
import java.util.Map;

/**
 * 智能门锁服务实现
 */
@Service
@Slf4j
@AllArgsConstructor
public class SmartLockServiceImpl implements SmartLockService {
    private final DeviceService deviceService;
    private final MessageChannel mqttOutboundChannel;

    @Override
    public Result<String> controlLock(String deviceId, String command) {
        // command: open/close
        Device device = getDeviceByDeviceId(deviceId);
        Map<String, Object> map = new HashMap<>();
        map.put("device_id", deviceId);
        map.put("command", command);
        String payload = JSON.toJSONString(map);
        log.info("发送门锁控制命令: {}", payload);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("mqtt_topic", "iot/device/control")
                .build();
        mqttOutboundChannel.send(message);
        return Result.ok("门锁命令发送成功");
    }

    @Override
    public Result<String> controlTouch(String deviceId, String command) {
        // command: touch-on/touch-off
        Device device = getDeviceByDeviceId(deviceId);
        Map<String, Object> map = new HashMap<>();
        map.put("device_id", deviceId);
        map.put("command", command);
        String payload = JSON.toJSONString(map);
        log.info("发送触控控制命令: {}", payload);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("mqtt_topic", "iot/device/control")
                .build();
        mqttOutboundChannel.send(message);
        return Result.ok("触控命令发送成功");
    }

    @Override
    public Result<String> controlSensor(String deviceId, String command) {
        // command: sensor-on/sensor-off
        Device device = getDeviceByDeviceId(deviceId);
        Map<String, Object> map = new HashMap<>();
        map.put("device_id", deviceId);
        map.put("command", command);
        String payload = JSON.toJSONString(map);
        log.info("发送红外感应控制命令: {}", payload);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("mqtt_topic", "iot/device/control")
                .build();
        mqttOutboundChannel.send(message);
        return Result.ok("红外感应命令发送成功");
    }

    private Device getDeviceByDeviceId(String deviceId) {
        QueryWrapper<Device> query = new QueryWrapper<>();
        query.eq("device_id", deviceId);
        Device device = deviceService.getOne(query);
        if (device == null) {
            throw new ServerException("设备不存在");
        }
        return device;
    }
}