package top.gx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Lenovo
 */
@Data
@Schema(description = "用户注册dto")
public class UserRegisterDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4176007248280129635L;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "验证码")
    private String code;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "租户编号")
    private String tenantId;
}
