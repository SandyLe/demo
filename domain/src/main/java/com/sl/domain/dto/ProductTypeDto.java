package com.sl.domain.dto;

import com.sl.domain.entity.Brand;
import com.sl.domain.entity.ProductType;

import java.util.List;

public class ProductTypeDto {

    private ProductType productType;
    private List<Brand> brandList;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }
}
