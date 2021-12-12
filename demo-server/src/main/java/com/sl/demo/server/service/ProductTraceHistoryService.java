package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.ProductTraceHistory;

import java.util.List;

public interface ProductTraceHistoryService {

    void save(ProductTraceHistory productTraceHistory);
    Pagination<ProductTraceHistory> findPage(Pagination<ProductTraceHistory> pagination);
    ProductTraceHistory findById(Long id);
    void delete(Long[] id);
    List<ProductTraceHistory> findHistories(String productCode, String traceCode);
    ProductTraceHistory findEarliest (String productCode, String traceCode);
}
