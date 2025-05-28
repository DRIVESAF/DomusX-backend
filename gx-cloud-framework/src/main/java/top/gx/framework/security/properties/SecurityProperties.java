package top.gx.framework.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lenovo
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mqxu.security")
public class SecurityProperties {
    /**
     * accessToken 过期时间(单位：秒)，默认2小时
     */
    private int accessTokenExpire = 60 * 60 * 2;
    /**
     * refreshToken 过期时间(单位：秒)，默认14天
     */
    private int refreshTokenExpire = 60 * 60 * 24 * 14;
}

