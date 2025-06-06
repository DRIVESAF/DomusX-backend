package top.gx.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import top.gx.dao.DeviceDataDao;
import top.gx.dao.DeviceThresholdRuleDao;
import top.gx.entity.Device;
import top.gx.entity.DeviceData;
import top.gx.entity.DeviceThresholdRule;
import top.gx.framework.common.exception.ServerException;
import top.gx.framework.common.utils.Result;
import top.gx.service.DeviceService;
import top.gx.service.PlantWateringService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 */
@Service
@Slf4j
@AllArgsConstructor
public class PlantWateringServiceImpl implements PlantWateringService {
  private final DeviceService deviceService;
  private final MessageChannel mqttOutboundChannel;
  private final DeviceThresholdRuleDao deviceThresholdRuleDao;
  private final DeviceDataDao deviceDataDao;

  @Override
  public Result<String> setWateringThreshold(String deviceId, float threshold) {
    // 检查设备是否存在
    QueryWrapper<Device> query = new QueryWrapper<>();
    query.eq("device_id", deviceId);
    Device device = deviceService.getOne(query);
    if (device == null) {
      throw new ServerException("设备不存在");
    }

    // 查询是否已存在规则
    QueryWrapper<DeviceThresholdRule> ruleQuery = new QueryWrapper<>();
    ruleQuery.eq("device_id", device.getId())
        .eq("sensor_type", "soil");
    DeviceThresholdRule existingRule = deviceThresholdRuleDao.selectOne(ruleQuery);

    DeviceThresholdRule rule;
    if (existingRule != null) {
      // 更新现有规则
      rule = existingRule;
      rule.setThreshold(threshold);
      deviceThresholdRuleDao.updateById(rule);
    } else {
      // 创建新规则
      rule = new DeviceThresholdRule();
      rule.setDeviceId(device.getId());
      rule.setSensorType("soil");
      rule.setThreshold(threshold);
      rule.setAction("on");
      rule.setIsEnabled(1);
      deviceThresholdRuleDao.insert(rule);
    }

    // 发送MQTT命令更新设备阈值
    Map<String, Object> map = new HashMap<>();
    map.put("device_id", deviceId);
    map.put("command", "set_threshold");
    map.put("soilThreshold", (int) threshold);
    String payload = JSON.toJSONString(map);
    log.info("发送阈值设置命令: {}", payload);
    Message<String> message = MessageBuilder.withPayload(payload)
        .setHeader("mqtt_topic", "iot/device/control")
        .build();
    mqttOutboundChannel.send(message);

    return Result.ok("阈值设置成功");
  }

  @Override
  public Result<String> setAutoWatering(String deviceId, boolean enabled) {
    // 检查设备是否存在
    QueryWrapper<Device> query = new QueryWrapper<>();
    query.eq("device_id", deviceId);
    Device device = deviceService.getOne(query);
    if (device == null) {
      throw new ServerException("设备不存在");
    }

    // 更新阈值规则状态
    UpdateWrapper<DeviceThresholdRule> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("device_id", device.getId())
        .eq("sensor_type", "soil")
        .set("is_enabled", enabled ? 1 : 0);
    deviceThresholdRuleDao.update(null, updateWrapper);

    // 发送MQTT命令更新自动模式
    Map<String, Object> map = new HashMap<>();
    map.put("device_id", deviceId);
    map.put("command", enabled ? "auto_on" : "auto_off");
    String payload = JSON.toJSONString(map);
    log.info("发送自动模式设置命令: {}", payload);
    Message<String> message = MessageBuilder.withPayload(payload)
        .setHeader("mqtt_topic", "iot/device/control")
        .build();
    mqttOutboundChannel.send(message);

    return Result.ok(enabled ? "自动浇水已启用" : "自动浇水已禁用");
  }

  @Override
  public Result<String> manualWatering(String deviceId, String command) {
    // 检查设备是否存在
    QueryWrapper<Device> query = new QueryWrapper<>();
    query.eq("device_id", deviceId);
    Device device = deviceService.getOne(query);
    if (device == null) {
      throw new ServerException("设备不存在");
    }

    // 发送MQTT命令控制浇水
    Map<String, Object> map = new HashMap<>();
    map.put("device_id", deviceId);
    map.put("command", command);
    String payload = JSON.toJSONString(map);
    log.info("发送手动控制命令: {}", payload);
    Message<String> message = MessageBuilder.withPayload(payload)
        .setHeader("mqtt_topic", "iot/device/control")
        .build();
    mqttOutboundChannel.send(message);

    return Result.ok("命令发送成功");
  }

  @Override
  public Result<Object> getWateringStatus(String deviceId) {
    // 检查设备是否存在
    QueryWrapper<Device> query = new QueryWrapper<>();
    query.eq("device_id", deviceId);
    Device device = deviceService.getOne(query);
    if (device == null) {
      throw new ServerException("设备不存在");
    }

    // 获取阈值规则
    QueryWrapper<DeviceThresholdRule> ruleQuery = new QueryWrapper<>();
    ruleQuery.eq("device_id", device.getId())
        .eq("sensor_type", "soil");

    DeviceThresholdRule rule = deviceThresholdRuleDao.selectOne(ruleQuery);
    if (rule != null) {
      Map<String, Object> status = new HashMap<>();
      status.put("threshold", rule.getThreshold());
      status.put("autoMode", rule.getIsEnabled() == 1);
      return Result.ok(status);
    }

    return Result.ok(null);
  }

  @Override
  public Result<Object> getLatestTemperatureAndHumidity(String deviceId) {
    // 检查设备是否存在
    QueryWrapper<Device> query = new QueryWrapper<>();
    query.eq("device_id", deviceId);
    Device device = deviceService.getOne(query);
    if (device == null) {
      throw new ServerException("设备不存在");
    }

    // 获取最新的温度数据
    QueryWrapper<DeviceData> tempQuery = new QueryWrapper<>();
    tempQuery.eq("device_id", device.getId())
        .eq("data_key", "temperature")
        .orderByDesc("record_time")
        .last("LIMIT 1");
    DeviceData tempData = deviceDataDao.selectOne(tempQuery);

    // 获取最新的湿度数据
    QueryWrapper<DeviceData> humidityQuery = new QueryWrapper<>();
    humidityQuery.eq("device_id", device.getId())
        .eq("data_key", "humidity")
        .orderByDesc("record_time")
        .last("LIMIT 1");
    DeviceData humidityData = deviceDataDao.selectOne(humidityQuery);

    Map<String, Object> result = new HashMap<>();
    if (tempData != null) {
      result.put("temperature", tempData.getDataValue());
      result.put("temperatureUnit", "℃");
      result.put("temperatureTime", tempData.getRecordTime());
    }
    if (humidityData != null) {
      result.put("humidity", humidityData.getDataValue());
      result.put("humidityUnit", "%");
      result.put("humidityTime", humidityData.getRecordTime());
    }

    return Result.ok(result);
  }
}