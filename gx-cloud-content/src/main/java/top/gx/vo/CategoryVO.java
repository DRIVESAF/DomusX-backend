package top.gx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import top.gx.framework.common.utils.DateUtils;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Lenovo
 */
@Data
@Schema(description = "分类")
public class CategoryVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6064089622173498243L;
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private Long id;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "封面图")
    private String cover;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;
}
