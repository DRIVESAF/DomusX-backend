package top.gx.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 设备数据视图对象
 */
@Data
public class DeviceDataVO {
  /**
   * 主键
   */
  private Integer id;

  /**
   * 设备ID
   */
  private Long deviceId;

  /**
   * 数据类型
   */
  private String dataKey;

  /**
   * 数据值
   */
  private String dataValue;

  /**
   * 单位
   */
  private String unit;

  /**
   * 采集时间
   */
  private LocalDateTime recordTime;
}