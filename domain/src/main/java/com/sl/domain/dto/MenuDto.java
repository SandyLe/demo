package com.sl.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.util.List;

@ApiModel(value = "菜单DTO")
public class MenuDto {
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "子菜单")
    private List<MenuDto> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MenuDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDto> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsOutSite(){
        if(StringUtils.hasText(getUrl())){
            return getUrl().startsWith("http");
        }
        return false;
    }
}
