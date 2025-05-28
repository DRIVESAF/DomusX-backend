package top.gx.service.impl;

import org.springframework.stereotype.Service;
import top.gx.dao.CategoryDao;
import top.gx.entity.Category;
import top.gx.framework.mybatis.service.impl.BaseServiceImpl;
import top.gx.service.CategoryService;

/**
 * @author Lenovo
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryDao, Category> implements CategoryService {
}
