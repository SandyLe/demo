package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.NewsRepository;
import com.sl.demo.server.repository.NewsTypeRepository;
import com.sl.demo.server.service.NewsService;
import com.sl.demo.server.util.LoginUtils;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.News;
import com.sl.domain.enums.RowSts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Override
    public void save(News news) {
        news.setRowSts(RowSts.NEW.getId());
        if(null == news.getId()){
            news.setCreateDate(new Date());
            news.setCreateUserId(LoginUtils.getLoginUser().getId());
        }
        news.setUpdateDate(new Date());
        news.setUpdateUserId(LoginUtils.getLoginUser().getId());
        newsRepository.save(news);
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findOne(id);
    }

    @Override
    public Pagination<News> findPage(Pagination<News> pagination) {
        return null;
    }

    @Override
    public List<News> findList(String newsTypeCode, Integer rowSts, Integer topN) {
        Specification<News> specification = new Specification<News>() {
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = Lists.newArrayList();
                if (StringUtils.hasText(newsTypeCode)){
                    list.add(cb.equal(root.get("newsTypeCode"), newsTypeCode));
                }
                if (null != rowSts){
                    list.add(cb.equal(root.get("rowSts"), rowSts));
                }
                query.where(list.toArray(new Predicate[]{}));
                return query.getRestriction();
            }
        };
        Pageable pageable = new PageRequest(0, topN, new Sort(Sort.Direction.DESC, "updateDate"));
        Page<News> newsPage = newsRepository.findAll(specification, pageable);
        return newsPage.getContent();
    }
}
