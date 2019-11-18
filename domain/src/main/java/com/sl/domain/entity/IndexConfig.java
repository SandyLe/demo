package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sl_index")
@ApiModel(value = "IndexConfig", description = "首页配置")
public class IndexConfig extends BaseEntity {

    @ApiModelProperty(value = "排序")
    private Integer weight;

    @ApiModelProperty(value = "图片")
    private String url;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
