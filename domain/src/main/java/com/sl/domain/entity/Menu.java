package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "sl_menu")
@ApiModel(value = "Menu", description = "用户")
public class Menu extends BaseEntity {

    @ApiModelProperty(value = "父菜单")
    private String parent;

    @ApiModelProperty(value = "菜单")
    private String level;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @Transient
    private Menu parentDto;

    public Menu getParentDto() {
        return parentDto;
    }

    public void setParentDto(Menu parentDto) {
        this.parentDto = parentDto;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
