package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sl_product_trace")
@ApiModel(value = "ProductTrace", description = "品牌溯源防伪")
public class ProductTrace extends BaseEntity {

    @ApiModelProperty("产品编码")
    private String productCode;
    @ApiModelProperty("产品")
    private Long productId;
    @Transient
    private Product product;

    @ApiModelProperty("溯源验证码")
    private String traceCode;

    @ApiModelProperty("来源")
    private String source;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTraceCode() {
        return traceCode;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
