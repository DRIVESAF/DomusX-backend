package top.gx.dao;

import org.apache.ibatis.annotations.Mapper;
import top.gx.entity.News;
import top.gx.framework.mybatis.dao.BaseDao;
import java.util.List;

@Mapper
public interface NewsDao extends BaseDao<News> {
    List<News> getList();
}