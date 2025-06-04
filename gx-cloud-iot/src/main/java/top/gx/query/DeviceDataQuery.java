package top.gx.query;

import lombok.Data;
import top.gx.framework.common.query.Query;

/**
 * 设备数据查询
 */
@Data
public class DeviceDataQuery extends Query {
  /**
   * 设备ID
   */
  private Long deviceId;

  /**
   * 数据类型
   */
  private String dataKey;

  /**
   * 开始时间
   */
  private String startTime;

  /**
   * 结束时间
   */
  private String endTime;

  public DeviceDataQuery() {
    // 设置默认分页参数
    setPage(1);
    setLimit(10);
  }
}