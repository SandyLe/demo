package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Brand;

import java.util.List;

public interface BrandService {
    void save(Brand brand);
    Pagination<Brand> findPage(Pagination<Brand> pagination);
    Brand findById(Long id);
    void delete(Long[] id);
    List<Brand> findList(List<String> codes, Integer rowSts);
    List<Brand> findList(List<String> codes, String productTypeCode, Integer rowSts);
    List<Brand> findByProductType(String productType);
}
