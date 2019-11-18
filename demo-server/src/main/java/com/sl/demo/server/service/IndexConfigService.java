package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.IndexConfig;

import java.util.List;

public interface IndexConfigService {
    void save(IndexConfig indexConfig);
    Pagination<IndexConfig> findPage(Pagination<IndexConfig> pagination);
    IndexConfig findById(Long id);
    void delete(Long[] id);
    List<IndexConfig> findList(Integer rowSts);
}
