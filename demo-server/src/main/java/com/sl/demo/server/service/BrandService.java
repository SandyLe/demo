package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Brand;

public interface BrandService {
    void save(Brand brand);
    Pagination<Brand> findPage(Pagination<Brand> pagination);
    Brand findById(Long id);
    void delete(Long[] id);
}
