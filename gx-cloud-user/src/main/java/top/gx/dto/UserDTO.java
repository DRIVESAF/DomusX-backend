package top.gx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Lenovo
 */
@Data
@Schema(description = "用户信息dto")
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -9218866391650258806L;
    @Schema(description = "用户名", required = true)
    private String username;
    @Schema(description = "密码", required = true)
    private String password;
    @Schema(description = "昵称", required = true)
    private String nickname;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "性别 0：男 1：⼥ 2：未知", required = true)
    @Range(min = 0, max = 2, message = "性别不正确")
    private Integer gender;
}
