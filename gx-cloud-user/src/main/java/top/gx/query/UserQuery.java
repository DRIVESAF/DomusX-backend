package top.gx.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gx.framework.common.query.Query;

/**
 * @author Lenovo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户查询")
public class UserQuery extends Query {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "手机号")
    private String mobile;
}
