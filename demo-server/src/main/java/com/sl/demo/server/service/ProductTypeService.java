package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.ProductType;

import java.util.List;

public interface ProductTypeService {

    void save (ProductType productType);
    Pagination<ProductType> findPage (Pagination<ProductType> pagination);
    ProductType findById (Long id);
    void delete (Long[] id);
    List<ProductType> findList(List<String> codes, Integer rowSts);
}
