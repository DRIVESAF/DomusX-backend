package top.gx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lenovo
 */
@Data
@TableName("t_user_device")
public class UserDevice {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Long userId;
    private Long deviceId;
    private Integer regionId;
    private Integer isOwner;
    private Integer deleteFlag;
    private LocalDateTime createTime;

}
