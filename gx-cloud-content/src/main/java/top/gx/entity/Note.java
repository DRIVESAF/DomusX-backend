package top.gx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gx.framework.mybatis.entity.BaseEntity;
import java.util.List;

/**
 * @author Lenovo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_note",autoResultMap = true)
public class Note extends BaseEntity {
    private Long userId;
    private Long categoryId;
    private String title;
    private String content;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> tags;
}
