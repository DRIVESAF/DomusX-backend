package top.gx.sms.service;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import top.gx.framework.common.cache.RedisCache;
import top.gx.framework.common.cache.RedisKeys;
import top.gx.framework.common.exception.ErrorCode;
import top.gx.framework.common.exception.ServerException;

/**
 * @author Lenovo
 */
public abstract class SmsService {
    @Resource
    private RedisCache redisCache;

    public abstract boolean sendSms(String mobile);

    public boolean verifyCode(String mobile, String code) {
        String captchaKey = RedisKeys.getCaptchaKey(mobile);
        String redisCode = (String) redisCache.get(captchaKey);
        //校验验证码合法性
        if (ObjectUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }
        //删除⽤过的验证码
        redisCache.delete(captchaKey);
        return true;
    }
}
