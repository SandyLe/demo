package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.ProductTypeRepository;
import com.sl.demo.server.service.ProductTypeService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.ProductType;
import com.sl.domain.enums.RowSts;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public void save(ProductType productType) {
        if(null == productType.getRowSts()){
            productType.setRowSts(RowSts.EFFECTIVE.getId());
        }
        productTypeRepository.save(productType);
    }

    @Override
    public Pagination<ProductType> findPage(Pagination<ProductType> pagination) {
        Page<ProductType> page = productTypeRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        pagination.setData(page.getContent());
        return pagination;
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id) {
        for (Long tempId : id) {
            productTypeRepository.delete(tempId);
        }
    }

    @Override
    public List<ProductType> findList(List<String> codes, Integer rowSts){
        Specification<ProductType> specification = new Specification<ProductType>() {
            List<Predicate> predicates = Lists.newArrayList();
            @Override
            public Predicate toPredicate(Root<ProductType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if(CollectionUtils.isNotEmpty(codes)){
                    predicates.add(root.<String>get("code").in(codes));
                }
                predicates.add(cb.equal(root.get("rowSts"), rowSts));
                query.where(predicates.toArray(new Predicate[]{}));
                return query.getRestriction();
            }
        };

        return productTypeRepository.findAll(specification);
    }
}
