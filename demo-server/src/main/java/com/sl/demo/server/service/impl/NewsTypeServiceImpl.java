package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.NewsTypeRepository;
import com.sl.demo.server.service.NewsTypeService;
import com.sl.demo.server.util.LoginUtils;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.NewsType;
import com.sl.domain.enums.RowSts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsTypeServiceImpl implements NewsTypeService {

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Override
    public void save(NewsType newsType) {
        newsType.setRowSts(RowSts.EFFECTIVE.getId());
        if(null == newsType.getId()){
            newsType.setCreateUserId(LoginUtils.getLoginUser().getId());
            newsType.setCreateDate(new Date());
        }
        newsType.setUpdateDate(new Date());
        newsTypeRepository.save(newsType);
    }

    @Override
    public NewsType findById(Long id) {
        return newsTypeRepository.findOne(id);
    }

    @Override
    public Pagination<NewsType> findPage(Pagination<NewsType> pagination) {
        Page<NewsType> page = newsTypeRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        pagination.setData(page.getContent());
        return pagination;
    }

    @Override
    public List<NewsType> findList(List<String> codeList) {
        return null;
    }
}
