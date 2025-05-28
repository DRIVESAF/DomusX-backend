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
@Schema(description = "标题查询")
public class NoteQuery extends Query {
    @Schema(description = "标题")
    private String title;
}
