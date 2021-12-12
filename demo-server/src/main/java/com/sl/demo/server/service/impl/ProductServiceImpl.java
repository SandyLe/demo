package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.ProductRepository;
import com.sl.demo.server.service.BrandService;
import com.sl.demo.server.service.ProductService;
import com.sl.demo.server.util.QRCodeUtils;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Brand;
import com.sl.domain.entity.Product;
import com.sl.domain.enums.RowSts;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandService brandService;

    @Value("${qCode.text}")
    private String text;

    @Value("${qCode.logoPath}")
    private String logoPath;

    @Value("${upload.path}")
    private String destPath;

    @Value("${upload.webPath}")
    private String webPath;

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

        Product product = productRepository.findOne(id);

        if (null != product) {
            Brand brand = brandService.findByCode(product.getBrandCode());
            product.setBrand(brand);
        }

        return product;
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            productRepository.delete(tempId);
        }
    }

    @Override
    public List<Product> findList(List<String> codes, String brandCode, Integer rowSts) {

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if(CollectionUtils.isNotEmpty(codes)){
                    predicates.add(root.<String>get("code").in(codes));
                }
                if(StringUtils.hasText(brandCode)){
                    predicates.add(cb.equal(root.get("brandCode"), brandCode));
                }
                predicates.add(cb.equal(root.get("rowSts"),rowSts));
                query.where(predicates.toArray(new Predicate[]{}));
                return query.getRestriction();
            }
        };
        return productRepository.findAll(specification);
    }

    @Override
    public Product findByCode(String code) {

        return productRepository.findByCode(code);
    }

    @Override
    public String generateQcode(Long id) throws Exception {

        Product product = findById(id);
        String url = text + product.getCode();
        String path = QRCodeUtils.encode(url, logoPath, destPath, true);
        String qCodePath = webPath + path;
        product.setQcode(qCodePath);
        save(product);
        return qCodePath;
    }
}
