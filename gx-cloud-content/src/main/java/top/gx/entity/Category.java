package top.gx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gx.framework.mybatis.entity.BaseEntity;

/**
 * @author Lenovo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_category")
public class Category extends BaseEntity{
    private String name;
    private String cover;
}
