package top.gx.service;

import top.gx.vo.NewsVO;
import top.gx.framework.mybatis.service.BaseService;
import top.gx.entity.News;
import java.util.List;

public interface NewsService extends BaseService<News> {
    List<NewsVO> listNewsVO();
}