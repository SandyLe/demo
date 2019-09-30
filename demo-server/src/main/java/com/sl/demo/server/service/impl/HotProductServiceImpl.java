package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.HotProductRepository;
import com.sl.demo.server.service.BrandService;
import com.sl.demo.server.service.HotProductService;
import com.sl.demo.server.service.ProductService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Brand;
import com.sl.domain.entity.HotProduct;
import com.sl.domain.entity.Product;
import com.sl.domain.enums.RowSts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
}