package top.gx.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gx.framework.common.query.Query;


/**
 * 用户-租户关联表查询
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户-租户关联表查询")
public class TUserTenantQuery extends Query {
}
