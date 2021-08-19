package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.ProductTraceRepository;
import com.sl.demo.server.service.ProductTraceService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.ProductTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTraceServiceImpl implements ProductTraceService {

    @Autowired
    private ProductTraceRepository productTraceRepository;

    @Override
    public void save(ProductTrace product) {
        productTraceRepository.save(product);
    }

    @Override
    public Pagination<ProductTrace> findPage(Pagination<ProductTrace> pagination) {
        Page<ProductTrace> page = productTraceRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        List<ProductTrace> datas = page.getContent();
        pagination.setData(datas);
        return pagination;
    }

    @Override
    public ProductTrace findById(Long id) {
        return productTraceRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            productTraceRepository.delete(tempId);
        }
    }

    @Override
    public ProductTrace findByProductTraceCode(String productCode, String traceCode) {

        return productTraceRepository.findByProductTraceCode(productCode, traceCode);
    }
}
