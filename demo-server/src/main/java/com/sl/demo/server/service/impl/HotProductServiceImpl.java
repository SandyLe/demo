package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.HotProductRepository;
import com.sl.demo.server.service.BrandService;
import com.sl.demo.server.service.HotProductService;
import com.sl.demo.server.service.ProductService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Brand;
import com.sl.domain.entity.HotProduct;
import com.sl.domain.entity.Product;
import com.sl.domain.enums.RowSts;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotProductServiceImpl implements HotProductService {

    @Autowired
    private HotProductRepository hotProductRepository;

    @Autowired
    private BrandService brandService;

    @Autowired
    private HotProductService hotProductService;

    @Autowired
    private ProductService productService;

    @Override
    public void save(HotProduct product) {
        if(null == product.getId()){
            product.setRowSts(10);
            product.setCreateDate(new Date());
        }
        product.setUpdateDate(new Date());
        hotProductRepository.save(product);
    }

    @Override
    public Pagination<HotProduct> findPage(Pagination<HotProduct> pagination) {
        Page<HotProduct> page = hotProductRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        List<HotProduct> datas = page.getContent();
        List<String> proCodes = datas.stream().map(HotProduct::getProductCode).collect(Collectors.toList());
        List<Product> products = productService.findList(proCodes, null, RowSts.EFFECTIVE.getId());
        List<String> brandCodes = products.stream().map(Product::getBrandCode).collect(Collectors.toList());
        List<Brand> brands = brandService.findList(brandCodes, RowSts.EFFECTIVE.getId());
        Map<String, Brand> brandMap = brands.stream().collect(Collectors.toMap(Brand::getCode,o->o));
        products.stream().forEach(o->{o.setBrand(brandMap.get(o.getBrandCode()));});
        Map<String, Product> productMap = products.stream().collect(Collectors.toMap(Product::getCode, o->o));
        datas.stream().forEach(o->{o.setProduct(productMap.get(o.getProductCode()));});
        pagination.setData(datas);
        return pagination;
    }

    @Override
    public HotProduct findById(Long id) {
        return hotProductRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            hotProductRepository.delete(tempId);
        }
    }

    @Override
    public List<Product> findTopList(Integer top, String brandCode) {
        Specification<HotProduct> specification = new Specification<HotProduct>() {
            @Override
            public Predicate toPredicate(Root<HotProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if(StringUtils.hasText(brandCode)){
                    predicates.add(cb.equal(root.get("brandCode"), brandCode));
                }
                predicates.add(cb.equal(root.get("rowSts"),RowSts.EFFECTIVE.getId()));
                query.where(predicates.toArray(new Predicate[]{}));
                return query.getRestriction();
            }
        };
        Pagination<HotProduct> pagination = new Pagination<HotProduct>();
        pagination.setColumn("weight");
        pagination.setPageSize(top);
        pagination.setOrder(Sort.Direction.ASC.name());
        Page<HotProduct> page = hotProductRepository.findAll(specification, pagination);
        List<String> productCodes = page.getContent().stream().map(HotProduct::getProductCode).collect(Collectors.toList());
        List<Product> products = productService.findList(productCodes, null, RowSts.EFFECTIVE.getId());
        List<String> brandCodes = products.stream().map(Product::getBrandCode).collect(Collectors.toList());
        List<Brand> brands = brandService.findList(brandCodes, RowSts.EFFECTIVE.getId());
        Map<String, Brand> brandMap = brands.stream().collect(Collectors.toMap(Brand::getCode,o->o));
        products.stream().forEach(o->{o.setBrand(brandMap.get(o.getBrandCode()));});
        return products;
    }
}
