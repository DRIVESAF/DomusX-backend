package top.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.gx.entity.UserDevice;
import top.gx.framework.mybatis.service.BaseService;

/**
 * @author Lenovo
 */
public interface UserService extends IService<UserDevice> {
    void bindDevice(Long deviceId,Long userId);
}
