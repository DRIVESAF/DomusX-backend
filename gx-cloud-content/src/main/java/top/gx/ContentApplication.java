package top.gx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Lenovo
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"top.gx.feign"})
public class ContentApplication {
    public static void main(String[] args) {
    SpringApplication.run(ContentApplication.class, args);
    }
}
