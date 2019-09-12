package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.News;
import com.sl.domain.entity.NewsType;

import java.util.List;

public interface NewsService {

    void save (News news);

    News findById(Long id);

    Pagination<News> findPage(Pagination<News> pagination);

    List<News> findList(String newsTypeCode, Integer rowSts, Integer topN);
}
