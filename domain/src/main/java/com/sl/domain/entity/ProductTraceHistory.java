package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sl_product_trace_history")
@ApiModel(value = "ProductTraceHistory", description = "品牌溯源防伪查询记录")
public class ProductTraceHistory extends BaseEntity {

    @ApiModelProperty("产品编码")
    private String productCode;
    @ApiModelProperty("产品品牌")
    private Long productId;

    @ApiModelProperty("溯源验证码")
    private String traceCode;
    @ApiModelProperty("搜索码")
    private String scanCode;

    @ApiModelProperty("地址")
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

    public String getScanCode() {
        return scanCode;
    }

    public void setScanCode(String scanCode) {
        this.scanCode = scanCode;
    }
}
