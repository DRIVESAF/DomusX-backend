package top.gx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import top.gx.framework.common.utils.DateUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Lenovo
 */
@Data
@Schema(description = "笔记")
public class NoteDetailVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1055853469432708285L;
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private Long id;
    @Schema(description = "userId")
    private Long userId;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "标签")
    private List<String> tags;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;
    @Schema(description = "作者昵称")
    private String nickname;
    @Schema(description = "作者头像")
    private String avatar;
}
