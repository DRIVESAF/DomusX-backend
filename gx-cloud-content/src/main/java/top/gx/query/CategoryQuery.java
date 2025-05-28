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
@Schema(description = "名称查询")
public class CategoryQuery extends Query {
    @Schema(description = "名称")
    private String name;
}
