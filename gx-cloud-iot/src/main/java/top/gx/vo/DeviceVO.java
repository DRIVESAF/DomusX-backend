package top.gx.vo;

/**
 * @author Lenovo
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.gx.framework.common.utils.DateUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "设备VO")
public class DeviceVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5229628667600458182L;
    @Schema(description = "id")
    private Long id;
    @Schema(description = "设备唯一标识")
    private String deviceId;
    @Schema(description = "设备名称")
    private String name;
    @Schema(description = "设备类型")
    private Integer type;
    @Schema(description = "设备状态")
    private Boolean status;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;
}
