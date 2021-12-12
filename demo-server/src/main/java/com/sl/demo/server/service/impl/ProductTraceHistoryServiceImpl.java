package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.ProductTraceHistoryRepository;
import com.sl.demo.server.service.ProductTraceHistoryService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.ProductTraceHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductTraceHistoryServiceImpl implements ProductTraceHistoryService {

    @Autowired
    private ProductTraceHistoryRepository productTraceHistoryRepository;

    @Override
    public void save(ProductTraceHistory product) {
        productTraceHistoryRepository.save(product);
    }

    @Override
    public Pagination<ProductTraceHistory> findPage(Pagination<ProductTraceHistory> pagination) {
        Page<ProductTraceHistory> page = productTraceHistoryRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        List<ProductTraceHistory> datas = page.getContent();
        pagination.setData(datas);
        return pagination;
    }

    @Override
    public ProductTraceHistory findById(Long id) {
        return productTraceHistoryRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            productTraceHistoryRepository.delete(tempId);
        }
    }

    @Override
    public List<ProductTraceHistory> findHistories(String productCode, String traceCode) {

        return productTraceHistoryRepository.findHistories(productCode, traceCode);
    }

    @Override
    public ProductTraceHistory findEarliest (String productCode, String traceCode) {

        return productTraceHistoryRepository.findEarliest(productCode, traceCode);
    }
}
