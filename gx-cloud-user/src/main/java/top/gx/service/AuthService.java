package top.gx.service;

import top.gx.dto.AccountLoginDTO;
import top.gx.dto.MobileLoginDTO;
import top.gx.vo.AccountLoginVO;
import top.gx.vo.MobileLoginVO;

/**
 * @author Lenovo
 */
public interface AuthService {
    /**
     *账号密码登录
     *
     * @param login 登录信息
     */
    AccountLoginVO loginByAccount(AccountLoginDTO login);
    /**
     *
     *⼿机短信登录
     * @param login 登录信息
     */
    MobileLoginVO loginByMobile(MobileLoginDTO login);
    /**
     *退出登录
     *
     * @param accessToken accessToken
     */
    void logout(String accessToken);
}
