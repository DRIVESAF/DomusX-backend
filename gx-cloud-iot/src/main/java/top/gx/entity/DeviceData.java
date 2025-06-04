package top.gx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 设备数据表
 */
@Data
@TableName("t_device_data")
public class DeviceData {
  /**
   * 主键
   */
  @TableId(type = IdType.AUTO)
  private Integer id;

  /**
   * 设备ID
   */
  private Long deviceId;

  /**
   * 数据类型，如 brightness、sound_level、temperature、humidity、soil_moisture
   */
  private String dataKey;

  /**
   * 数据值
   */
  private String dataValue;

  /**
   * 单位，如 lux、dB、℃
   */
  private String unit;

  /**
   * 采集时间
   */
  private LocalDateTime recordTime;
}