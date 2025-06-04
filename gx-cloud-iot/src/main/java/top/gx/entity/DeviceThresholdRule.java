package top.gx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 设备自动控制规则表
 */
@Data
@TableName("t_device_threshold_rule")
public class DeviceThresholdRule {
  /**
   * 规则ID
   */
  @TableId(type = IdType.AUTO)
  private Integer id;

  /**
   * 设备ID
   */
  private Long deviceId;

  /**
   * 传感器类型，如light、temperature
   */
  private String sensorType;

  /**
   * 比较符号，如<、>、=
   */
  private String operator;

  /**
   * 阈值
   */
  private Float threshold;

  /**
   * 执行动作，如turn on、turn off
   */
  private String action;

  /**
   * 规则是否启用
   */
  private Integer isEnabled;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;

  /**
   * 更新时间
   */
  private LocalDateTime updateTime;
}