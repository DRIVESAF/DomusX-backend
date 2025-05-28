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
@TableName("t_device")
public class Device extends BaseEntity {
    private String deviceId;
    private String name;
    private Integer type;
    private Boolean status;
}
