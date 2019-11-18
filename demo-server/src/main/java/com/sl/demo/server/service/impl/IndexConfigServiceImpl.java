package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.IndexConfigRepository;
import com.sl.demo.server.service.IndexConfigService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.IndexConfig;
import com.sl.domain.enums.RowSts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexConfigServiceImpl implements IndexConfigService {

    @Autowired
    private IndexConfigRepository indexConfigRepository;

    @Override
    public void save(IndexConfig indexConfig) {

        indexConfig.setRowSts((null == indexConfig.getRowSts() ? RowSts.EFFECTIVE.getId() : indexConfig.getRowSts()));
        indexConfigRepository.save(indexConfig);
    }

    @Override
    public Pagination<IndexConfig> findPage(Pagination<IndexConfig> pagination) {
        Page<IndexConfig> page = indexConfigRepository.findAll(pagination);
        pagination.setData(page.getContent());
        pagination.setTotalRecords(page.getTotalElements());
        return pagination;
    }

    @Override
    public IndexConfig findById(Long id) {
        return indexConfigRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id) {
        for(Long tempId: id){
            indexConfigRepository.delete(tempId);
        }
    }

    @Override
    public List<IndexConfig> findList(Integer rowSts) {
        return indexConfigRepository.findList(rowSts);
    }
}
