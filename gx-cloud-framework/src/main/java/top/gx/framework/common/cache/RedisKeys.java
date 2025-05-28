package top.gx.framework.common.cache;

/**
 * @author Lenovo
 */
public class RedisKeys {
    /**
     * 验证码Key
     */
    public static String getCaptchaKey(String key) {
        return "api:captcha:" + key;
    }

    /**
     * accessToken Key
     */
    public static String getAccessTokenKey(String accessToken) {
        return "api:token:" + accessToken;
    }
}
