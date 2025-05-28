package top.gx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
/**
 * @author Lenovo
 */
@Data
@Schema(description = "账号登录")
public class AccountLoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3151154665736197138L;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
}
