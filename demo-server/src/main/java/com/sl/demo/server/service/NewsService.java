package com.sl.demo.server.service;

import com.sl.domain.dto.NewsPagination;
import com.sl.domain.entity.News;
import com.sl.domain.entity.NewsType;

import java.util.List;

public interface NewsService {

    void save (News news);

    News findById(Long id);

    NewsPagination findPage(NewsPagination pagination);

    List<News> findList(String newsTypeCode, Integer rowSts, Integer topN);

    void delete(Long[] id);

    void updateSts(Long id, Integer rowSts);
}
