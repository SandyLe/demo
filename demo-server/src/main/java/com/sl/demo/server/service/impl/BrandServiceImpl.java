package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.BrandRepository;
import com.sl.demo.server.service.BrandService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public void save(Brand brand) {
        if(null == brand.getId()){
            brand.setRowSts(10);
            brand.setCreateDate(new Date());
        }
        brandRepository.save(brand);
    }

    @Override
    public Pagination<Brand> findPage(Pagination<Brand> pagination) {
        Page<Brand> page = brandRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        pagination.setData(page.getContent());
        return pagination;
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            brandRepository.delete(tempId);
        }
    }
}
