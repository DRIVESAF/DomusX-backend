package top.gx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Lenovo
 */
@Data
@Schema(description = "手机号登录")
public class MobileLoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2025372959357317449L;

    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "验证码")
    private String code;
}
