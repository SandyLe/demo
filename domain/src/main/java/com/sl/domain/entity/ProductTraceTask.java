package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sl_product_trace_task")
@ApiModel(value = "ProductTraceTask", description = "品牌溯源防伪人物")
public class ProductTraceTask extends BaseEntity {

    @ApiModelProperty("产品编码")
    private String productCode;
    @ApiModelProperty("产品")
    private Long productId;
    @Transient
    private Product product;

    @ApiModelProperty("前缀")
    private String prefix;

    @ApiModelProperty("起始值")
    private Integer start;

    @ApiModelProperty("结束值")
    private Integer end;

    @ApiModelProperty("中间长度")
    private Integer midCount;

    @ApiModelProperty("末尾随机数长度")
    private Integer endCount;

    @ApiModelProperty("生成")
    private Integer isGen;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getMidCount() {
        return midCount;
    }

    public void setMidCount(Integer midCount) {
        this.midCount = midCount;
    }

    public Integer getEndCount() {
        return endCount;
    }

    public void setEndCount(Integer endCount) {
        this.endCount = endCount;
    }

    public Integer getIsGen() {
        return isGen;
    }

    public void setIsGen(Integer isGen) {
        this.isGen = isGen;
    }
}
