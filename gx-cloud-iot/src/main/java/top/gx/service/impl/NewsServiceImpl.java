package top.gx.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.gx.dao.NewsDao;
import top.gx.entity.News;
import top.gx.service.NewsService;
import top.gx.vo.NewsVO;
import top.gx.framework.mybatis.service.impl.BaseServiceImpl;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsServiceImpl extends BaseServiceImpl<NewsDao, News> implements NewsService {
    private final NewsDao newsDao;

    @Override
    public List<NewsVO> listNewsVO() {
        List<News> newsList = newsDao.getList();
        return newsList.stream().map(news -> {
            NewsVO vo = new NewsVO();
            vo.setId(news.getId());
            vo.setTitle(news.getTitle());
            vo.setContent(news.getContent());
            vo.setUpdateTime(news.getUpdateTime());
            vo.setViewCount(news.getViewCount());
            vo.setCoverImage(news.getCoverImage());
            vo.setSource(news.getSource());
            return vo;
        }).collect(Collectors.toList());
    }
}