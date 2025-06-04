package top.gx.service;

import top.gx.framework.common.utils.PageResult;
import top.gx.framework.mybatis.service.BaseService;
import top.gx.entity.DeviceData;
import top.gx.query.DeviceDataQuery;
import top.gx.vo.DeviceDataVO;

/**
 * 设备数据服务
 */
public interface DeviceDataService extends BaseService<DeviceData> {
  /**
   * 分页查询
   */
  PageResult<DeviceDataVO> page(DeviceDataQuery query);

  /**
   * 保存设备数据（内部使用）
   */
  void saveDeviceData(Long deviceId, String dataKey, String dataValue, String unit);
}