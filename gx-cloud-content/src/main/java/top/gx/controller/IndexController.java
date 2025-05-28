package top.gx.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lenovo
 */
@Tag(name = "首页")
@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "项目已启动，祝您使用愉快！";
    }
}
