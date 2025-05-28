package top.gx.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.gx.convert.UserConvert;
import top.gx.dao.UserDao;
import top.gx.entity.UserEntity;
import top.gx.service.MyUserDetailsService;

/**
 * @author Lenovo
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    private final MyUserDetailsService myUserDetailsService;
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.getByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名或密码错误 ");
        }
        return myUserDetailsService.getUserDetails(UserConvert.INSTANCE.convertDetail(userEntity));
    }
}
