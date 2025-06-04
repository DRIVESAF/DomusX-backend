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
import top.gx.service.DeviceDataService;
import top.gx.service.DeviceService;
import top.gx.service.LightService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 */

@Service
@Slf4j
@AllArgsConstructor
public class LightServiceImpl implements LightService {
    private final DeviceService deviceService;
    private final MessageChannel mqttOutboundChannel;
<<<<<<< HEAD
=======
    private final LightServiceImpl lightServiceImpl;
>>>>>>> 10f2d49 (灯光模块编写)

    @Override
    public void sendCommand(String deviceId, String command, Integer value) {
        QueryWrapper<Device> query = new QueryWrapper<>();
        query.eq("device_id", deviceId);
        Device device = deviceService.getOne(query);
        if (device == null) {
            throw new ServerException("设备不存在");
        }
        // 构建JSON命令
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", deviceId);
        map.put("command", command);
        map.put("value", value);
        String payload = JSON.toJSONString(map);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("mqtt_topic", "iot/device/control")
                .build();
        mqttOutboundChannel.send(message);
    }

    @Override
    public Result<String> setLightThreshold(String deviceId, Integer value) {
<<<<<<< HEAD
        sendCommand(deviceId,"setLightThreshold",value);
=======
        lightServiceImpl.sendCommand(deviceId,"setLightThreshold",value);
>>>>>>> 10f2d49 (灯光模块编写)
        return Result.ok("阈值设置成功");
    }

    @Override
    public Result<String> setColor(String deviceId, Integer value) {
<<<<<<< HEAD
        sendCommand(deviceId,"setColor",value);
=======
        lightServiceImpl.sendCommand(deviceId,"setColor",value);
>>>>>>> 10f2d49 (灯光模块编写)
        return Result.ok("灯光颜色设置成功");
    }

    @Override
    public Result<String> setSwitch(String deviceId, Integer value) {
<<<<<<< HEAD
        sendCommand(deviceId,"setSwitch",value);
=======
        lightServiceImpl.sendCommand(deviceId,"setSwitch",value);
>>>>>>> 10f2d49 (灯光模块编写)
        return Result.ok("电源设置成功");
    }

    @Override
    public Result<String> setSound(String deviceId, Integer value) {
<<<<<<< HEAD
        sendCommand(deviceId,"setSound",value);
=======
        lightServiceImpl.sendCommand(deviceId,"setSound",value);
>>>>>>> 10f2d49 (灯光模块编写)
        return Result.ok("声控模式设置成功");
    }

    @Override
    public Result<String> setLight(String deviceId, Integer value) {
<<<<<<< HEAD
        sendCommand(deviceId,"setLight",value);
=======
        lightServiceImpl.sendCommand(deviceId,"setLight",value);
>>>>>>> 10f2d49 (灯光模块编写)
        return Result.ok("光控模式设置成功");
    }

    @Override
    public Result<String> setAuto(String deviceId, Integer value) {
<<<<<<< HEAD
        sendCommand(deviceId,"setAuto",value);
=======
        lightServiceImpl.sendCommand(deviceId,"setAuto",value);
>>>>>>> 10f2d49 (灯光模块编写)
        return Result.ok("设置自动模式设置成功");
    }
}
