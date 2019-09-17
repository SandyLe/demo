package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.NewsType;

import java.util.List;

public interface NewsTypeService {

    void save (NewsType newsType);

    NewsType findById(Long id);

    Pagination<NewsType> findPage(Pagination<NewsType> pagination);

    List<NewsType> findList(List<String> codeList);

    void delete(Long[] id);
}
