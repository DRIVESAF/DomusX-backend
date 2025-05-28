package top.gx.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gx.convert.CategoryConvert;
import top.gx.entity.Category;
import top.gx.framework.common.utils.Result;
import top.gx.service.CategoryService;
import top.gx.vo.CategoryVO;

import java.util.List;
/**
 * @author Lenovo
 */
@RestController
@RequestMapping("api/category")
@Tag(name = "分类模块")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("list")
    public Result<List<CategoryVO>> getCategoryList() {
        List<Category> categories = categoryService.list();
        List<CategoryVO> list = CategoryConvert.INSTANCE.convertList(categories);
        return Result.ok(list);
    }
}
