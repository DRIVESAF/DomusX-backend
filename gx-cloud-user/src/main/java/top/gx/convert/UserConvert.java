package top.gx.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.gx.dto.MobileLoginDTO;
import top.gx.dto.UserDTO;
import top.gx.dto.UserRegisterDTO;
import top.gx.entity.UserEntity;
import top.gx.framework.security.user.UserDetail;
import top.gx.vo.UserVO;

/**
 * @author Lenovo
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);
    UserVO convert(UserEntity entity);
    UserEntity convert(UserDTO dto);
    UserVO convert(UserDetail userDetail);
    UserEntity convert(MobileLoginDTO dto);
    UserDetail convertDetail(UserEntity entity);
    UserEntity convert(UserRegisterDTO user);
}
