package top.gx.framework.security.mobile;

/**
 * @author Lenovo
 */
public interface MobileVerifyCodeService {
    boolean verifyCode(String mobile, String code);
}
