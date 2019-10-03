package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.HotProduct;
import com.sl.domain.entity.Product;

import java.util.List;

public interface HotProductService {
    void save(HotProduct product);
    Pagination<HotProduct> findPage(Pagination<HotProduct> pagination);
    HotProduct findById(Long id);
    void delete(Long[] id);
    List<Product> findTopList(Integer top);
}
