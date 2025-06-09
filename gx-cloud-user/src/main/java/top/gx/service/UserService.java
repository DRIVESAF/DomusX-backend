package top.gx.service;

import top.gx.dto.UserDTO;
import top.gx.entity.UserEntity;
import top.gx.framework.mybatis.service.BaseService;
import top.gx.vo.UserVO;

/**
 * @author Lenovo
 */
public interface UserService extends BaseService<UserEntity> {
    void save(UserDTO vo);
    void update(UserDTO dto);
    UserVO getByMobile(String mobile);
    UserVO getByUsername(String username);
    UserVO getById(Long id);
    void bindTenant(String tenantId,Long userId);
}
