package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.BrandRepository;
import com.sl.demo.server.service.BrandService;
import com.sl.demo.server.service.ProductTypeService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Brand;
import com.sl.domain.entity.ProductType;
import com.sl.domain.enums.RowSts;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductTypeService productTypeService;

    @Override
    public void save(Brand brand) {
        if(null == brand.getId()){
            brand.setRowSts(10);
            brand.setCreateDate(new Date());
        }
        brandRepository.save(brand);
    }

    @Override
    public Brand findByCode(String code) {

        return brandRepository.findByCode(code);
    }

    @Override
    public Pagination<Brand> findPage(Pagination<Brand> pagination) {
        Page<Brand> page = brandRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        List<Brand> datas = page.getContent();
        List<String> prodTypeCodes = datas.stream().map(o->o.getProductTypeCode()).collect(Collectors.toList());
        List<ProductType> productTypes = productTypeService.findList(prodTypeCodes, RowSts.EFFECTIVE.getId());
        Map<String, ProductType> productTypeMap = productTypes.stream().collect(Collectors.toMap(ProductType::getCode, o->o));
        datas.stream().forEach(o->{
            o.setProductType(productTypeMap.get(o.getProductTypeCode()));
        });
        pagination.setData(datas);
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
    public List<Brand> findList(List<String> codeList, String productTypeCode, Integer rowSts) {

        Specification<Brand> specification = new Specification<Brand>() {
            @Override
            public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(cb.equal(root.get("rowSts"), rowSts));
                if(CollectionUtils.isNotEmpty(codeList)){
                    predicates.add(root.<String>get("code").in(codeList));
                }
                if(StringUtils.hasText(productTypeCode)){
                    predicates.add(cb.equal(root.<String>get("productTypeCode"), productTypeCode));
                }
                query.where(predicates.toArray(new Predicate[]{}));
                query.orderBy(cb.desc(root.get("productTypeCode")));
                return query.getRestriction();
            }
        };
        return brandRepository.findAll(specification);
    }

    @Override
    public List<Brand> findList(List<String> codeList, Integer rowSts) {

        return findList(codeList, null, rowSts);
    }
    @Override
    public List<Brand> findByProductType(String productType){
        return brandRepository.findByProductType(productType);
    }
}
