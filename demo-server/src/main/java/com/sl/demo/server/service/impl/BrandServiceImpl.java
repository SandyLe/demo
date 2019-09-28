package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.BrandRepository;
import com.sl.demo.server.service.BrandService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Brand;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Brand> findList(List<String> codeList, Integer rowSts) {

        Specification<Brand> specification = new Specification<Brand>() {
            @Override
            public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(cb.equal(root.get("rowSts"), rowSts));
                if(CollectionUtils.isNotEmpty(codeList)){
                    predicates.add(root.<String>get("code").in(codeList));
                }
                query.where(predicates.toArray(new Predicate[]{}));
                return query.getRestriction();
            }
        };
        return brandRepository.findAll(specification);
    }
}
