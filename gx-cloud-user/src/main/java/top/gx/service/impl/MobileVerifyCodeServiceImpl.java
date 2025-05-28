package top.gx.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.gx.framework.security.mobile.MobileVerifyCodeService;
import top.gx.sms.service.AliyunSmsService;

/**
 * @author Lenovo
 */
@Service
@AllArgsConstructor
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {
    private final AliyunSmsService smsService;
    @Override
    public boolean verifyCode(String mobile, String code) {
        return smsService.verifyCode(mobile, code);
    }
}
