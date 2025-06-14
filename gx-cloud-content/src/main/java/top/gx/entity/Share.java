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
@TableName("t_share")
public class Share  extends BaseEntity {
    private Integer userId;
    private String title;
    private Integer isOriginal;
    private String author;
    private String cover;
    private String summary;
    private Integer price;
    private String downloadUrl;
    private Integer buyCount;
    private Integer showFlag;
    private String auditStatus;
    private String reason;
}
