package top.gx.storage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @author Lenovo
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private String endPoint;
    private String bucketName;
}
