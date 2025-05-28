package top.gx.sms.service;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import top.gx.framework.common.cache.RedisCache;
import top.gx.framework.common.cache.RedisKeys;
import top.gx.framework.common.exception.ErrorCode;
import top.gx.framework.common.exception.ServerException;
import top.gx.sms.config.AliyunSmsConfig;
import top.gx.sms.utils.SmsUtils;

/**
 * @author Lenovo
 */
@Service
@AllArgsConstructor
@Slf4j
public class AliyunSmsService extends SmsService{
    private final AliyunSmsConfig aliyunSmsConfig;
    private final RedisCache redisCache;

    @Override
    public boolean sendSms (String mobile) {
        // 校验手机号合法性
        if (!SmsUtils. checkPhone(mobile)) {
            throw new ServerException(ErrorCode.PARAMS_ERROR);
        }
        try {
            // 创建配置对象
            Config config = new Config()
                        .setAccessKeyId(aliyunSmsConfig.getAccessKey( ))
                        .setAccessKeySecret(aliyunSmsConfig.getAccessKeySecret());
                // 创建客户端对象
                Client client = new Client(config);
                String code = RandomStringUtils.randomNumeric(4);
                //创建请求对象
                SendSmsRequest request = new SendSmsRequest()
                        .setSignName(aliyunSmsConfig. getSignName( ))
                        .setTemplateCode(aliyunSmsConfig. getTemplateCode( ))
                        .setPhoneNumbers (mobile)
                        .setTemplateParam("{\"code\":\"" + code + "\"}");
                // 发送短信
                SendSmsResponse response = client.sendSmsWithOptions(request, new RuntimeOptions());
                SendSmsResponseBody body = response.getBody( );
                // 检查响应状态
                if ("OK".equals(body.getCode( )) ) {
                    log. info(" ============= 短信发送成功 ============= ");
                // redis缓存验证码
                    redisCache.set(RedisKeys.getCaptchaKey(mobile), code, 60);
                    return true;
                } else {
                    log.error("短信发送失败,错误码:{},错误信息:{}", body.getCode(), body.getMessage());
                    return false;
                }
        } catch (Exception e) {
            return false;
        }
    }
}
