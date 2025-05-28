package top.gx.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.gx.framework.common.utils.Result;
import top.gx.vo.UserVO;

/**
 * @author Lenovo
 */
@FeignClient(name = "gx-cloud-user")
public interface UserService {
    @GetMapping(value = "/api/user/getUserById")
    Result<UserVO> getUserById(@RequestParam("id") Long id);
}
