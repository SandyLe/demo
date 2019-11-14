package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sl_hot_product")
@ApiModel(value = "HotProduct", description = "热销产品")
public class HotProduct extends BaseEntity {

    @ApiModelProperty("产品品牌Code")
    private String brandCode;

    @ApiModelProperty("产品Code")
    private String productCode;

    @ApiModelProperty(value = "排序")
    private Integer weight;

    @Transient
    private Product product;

    @Transient
    private Brand brand;

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
