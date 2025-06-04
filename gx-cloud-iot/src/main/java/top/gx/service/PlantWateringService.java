package top.gx.service;

import top.gx.framework.common.utils.Result;

/**
 * @author Lenovo
 */
public interface PlantWateringService {
  /**
   * 设置浇水阈值
   * 
   * @param deviceId  设备ID
   * @param threshold 阈值
   */
  Result<String> setWateringThreshold(String deviceId, float threshold);

  /**
   * 设置自动浇水状态
   * 
   * @param deviceId 设备ID
   * @param enabled  是否启用
   */
  Result<String> setAutoWatering(String deviceId, boolean enabled);

  /**
   * 手动浇水控制
   * 
   * @param deviceId 设备ID
   * @param command  命令 (on/off)
   */
  Result<String> manualWatering(String deviceId, String command);

  /**
   * 获取浇水状态
   * 
   * @param deviceId 设备ID
   */
  Result<Object> getWateringStatus(String deviceId);
}