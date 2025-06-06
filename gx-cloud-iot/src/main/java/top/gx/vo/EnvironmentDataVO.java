package top.gx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 环境数据VO
 */
@Data
@Schema(description = "环境数据")
public class EnvironmentDataVO {
  @Schema(description = "温度")
  private String temperature;

  @Schema(description = "湿度")
  private String humidity;

  @Schema(description = "土壤湿度")
  private String soilMoisture;

  @Schema(description = "单位")
  private String unit;

  @Schema(description = "记录时间")
  private LocalDateTime recordTime;
}