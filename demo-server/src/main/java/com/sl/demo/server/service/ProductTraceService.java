package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.ProductTrace;

public interface ProductTraceService {

    void save(ProductTrace productTrace);
    Pagination<ProductTrace> findPage(Pagination<ProductTrace> pagination);
    ProductTrace findById(Long id);
    void delete(Long[] id);
    ProductTrace findByProductTraceCode(String productCode, String traceCode);
}
