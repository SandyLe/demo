package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.ProductRepository;
import com.sl.demo.server.service.BrandService;
import com.sl.demo.server.service.ProductService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Brand;
import com.sl.domain.entity.Product;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandService brandService;

    @Override
    public void save(Product product) {
        if(null == product.getId()){
            product.setRowSts(10);
            product.setCreateDate(new Date());
        }
        product.setUpdateDate(new Date());
        productRepository.save(product);
    }

    @Override
    public Pagination<Product> findPage(Pagination<Product> pagination) {
        Page<Product> page = productRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        List<Product> datas = page.getContent();
        List<String> brandCodes = datas.stream().map(Product::getBrandCode).collect(Collectors.toList());
        List<Brand> brands = brandService.findList(brandCodes, RowSts.EFFECTIVE.getId());
        Map<String, Brand> brandMap = brands.stream().collect(Collectors.toMap(Brand::getCode,o->o));
        datas.stream().forEach(o->{o.setBrand(brandMap.get(o.getBrandCode()));});
        pagination.setData(datas);
        return pagination;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            productRepository.delete(tempId);
        }
    }

    @Override
    public List<Product> findList(List<String> codes, Integer rowSts) {

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if(CollectionUtils.isNotEmpty(codes)){
                    predicates.add(root.<String>get("code").in(codes));
                }
                predicates.add(cb.equal(root.get("rowSts"),rowSts));
                query.where(predicates.toArray(new Predicate[]{}));
                return query.getRestriction();
            }
        };
        return productRepository.findAll(specification);
    }
}
