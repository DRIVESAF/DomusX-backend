package top.gx.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @author Lenovo
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliyunSmsConfig {
    private String accessKey;
    private String accessKeySecret;
    private String signName;
    private String templateCode;
}
