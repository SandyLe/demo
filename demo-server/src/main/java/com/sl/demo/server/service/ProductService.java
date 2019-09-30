package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    Pagination<Product> findPage(Pagination<Product> pagination);
    Product findById(Long id);
    void delete(Long[] id);
    List<Product> findList(List<String> codes, String brandCode, Integer rowSts);
}
