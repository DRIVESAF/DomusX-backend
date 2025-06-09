package top.gx.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户-租户关联表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */

@Data
@TableName("t_user_tenant")
public class TUserTenantEntity {
    @TableId
    @TableField(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 租户编码
     */
    @TableField(value = "tenant_id")
    private String tenantId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除：0-正常 1-删除
     */
    @TableLogic
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Integer deleted;

}
