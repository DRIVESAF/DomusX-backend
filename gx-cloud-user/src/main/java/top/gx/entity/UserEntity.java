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
@TableName("t_user")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String mobile;
    private String nickname;
    private String avatar;
    private String email;
    private Integer gender;
    private Integer status;
}
