package top.gx.framework.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lenovo
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"top.gx.**"};
        return GroupedOpenApi.builder().group("GxCloud")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("915826506@qq.com");

        return new OpenAPI().info(new Info()
                .title("GxCloud")
                .description("GxCloud")
                .contact(contact)
                .version("1.0")
                .termsOfService("https://gx.top")
                .license(new License().name("MIT")
                        .url("https://gx.top")));
    }
}
