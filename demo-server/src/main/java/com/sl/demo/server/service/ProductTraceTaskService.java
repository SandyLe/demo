package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.ProductTraceTask;

public interface ProductTraceTaskService {

    void save(ProductTraceTask productTrace);
    Pagination<ProductTraceTask> findPage(Pagination<ProductTraceTask> pagination);
    ProductTraceTask findById(Long id);
    void delete(Long[] id);
    void genTraceCode(Long[] id);
    ProductTraceTask findByProductTraceCode(String productCode, String traceCode);
}
