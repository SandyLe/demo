package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sl_news")
@ApiModel(value = "News", description = "新闻")
public class News extends BaseEntity {

    @ApiModelProperty(value = "类型")
    private String newsTypeCode;
    @ApiModelProperty(value = "内容")
    private String content;

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
}