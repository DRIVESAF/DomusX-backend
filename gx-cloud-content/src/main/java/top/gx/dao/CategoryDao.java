package top.gx.dao;

import org.apache.ibatis.annotations.Mapper;
import top.gx.entity.Category;
import top.gx.framework.mybatis.dao.BaseDao;

/**
 * @author Lenovo
 */
@Mapper
public interface CategoryDao extends BaseDao<Category> {
}
