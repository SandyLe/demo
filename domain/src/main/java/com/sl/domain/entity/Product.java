package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sl_product")
@ApiModel(value = "Product", description = "品牌产品")
public class Product extends BaseEntity {

    @ApiModelProperty("产品品牌")
    private String brandCode;

    @Transient
    private Brand brand;

    @ApiModelProperty("主图URL")
    private String imgUrl;

    @ApiModelProperty("商铺URL")
    private String shopUrl;

    @ApiModelProperty("总代理")
    private String agent;

    @ApiModelProperty("生产批号")
    private String batchNo;

    @ApiModelProperty("保质期")
    private String expirationDate;

    @ApiModelProperty("产品成分")
    private String ingredient;

    @ApiModelProperty("原产地")
    private String sourceArea;

    @ApiModelProperty("使用方法")
    private String useMethod;

    @ApiModelProperty("型号")
    private String spectxt;

    @ApiModelProperty("星级评论")
    private String starPic;

    @ApiModelProperty("二维码")
    private String qcode;

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

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

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getSourceArea() {
        return sourceArea;
    }

    public void setSourceArea(String sourceArea) {
        this.sourceArea = sourceArea;
    }

    public String getUseMethod() {
        return useMethod;
    }

    public void setUseMethod(String useMethod) {
        this.useMethod = useMethod;
    }

    public String getSpectxt() {
        return spectxt;
    }

    public void setSpectxt(String spectxt) {
        this.spectxt = spectxt;
    }

    public String getStarPic() {
        return starPic;
    }

    public void setStarPic(String starPic) {
        this.starPic = starPic;
    }

    public String getQcode() {
        return qcode;
    }

    public void setQcode(String qcode) {
        this.qcode = qcode;
    }
}
