package top.gx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Lenovo
 */
@Data
@Schema(description = "用户vo")
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -395117600515434275L;
    @Schema(description = "id")
    private Long id;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "姓名")
    private String nickname;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "性别 0：男 1：⼥ 2：未知")
    private Integer gender;
}
