package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.NewsRepository;
import com.sl.demo.server.repository.NewsTypeRepository;
import com.sl.demo.server.service.NewsService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Override
    public void save(News news) {

    }

    @Override
    public News findById(Long id) {
        return null;
    }

    @Override
    public Pagination<News> findPage(Pagination<News> pagination) {
        return null;
    }

    @Override
    public List<News> findList(String newsTypeCode) {
        return null;
    }
}
