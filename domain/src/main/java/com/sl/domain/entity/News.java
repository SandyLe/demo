package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sl_news")
@ApiModel(value = "News", description = "新闻")
public class News extends BaseEntity {

    @ApiModelProperty(value = "类型")
    private String newsTypeCode;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "内容")
    private Integer mainImgNum;
    @ApiModelProperty(value = "内容")
    private String mainImgUrl;

    @Transient
    private NewsType newsType;

    public String getNewsTypeCode() {
        return newsTypeCode;
    }

    public void setNewsTypeCode(String newsTypeCode) {
        this.newsTypeCode = newsTypeCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    public Integer getMainImgNum() {
        return mainImgNum;
    }

    public void setMainImgNum(Integer mainImgNum) {
        this.mainImgNum = mainImgNum;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }
}
