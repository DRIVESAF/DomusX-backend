package top.gx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gx.framework.common.utils.Result;
import top.gx.service.NewsService;
import top.gx.vo.NewsVO;
import java.util.List;

@RestController
@RequestMapping("api/news")
@Tag(name = "资讯模块")
@AllArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/list")
    @Operation(summary = "获取资讯列表")
    public Result<List<NewsVO>> list() {
        return Result.ok(newsService.listNewsVO());
    }
}