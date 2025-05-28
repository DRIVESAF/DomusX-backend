package top.gx.service;

import org.springframework.security.core.userdetails.UserDetails;
import top.gx.framework.security.user.UserDetail;

/**
 * @author Lenovo
 */
public interface MyUserDetailsService {
    UserDetails getUserDetails(UserDetail userDetail);
}
