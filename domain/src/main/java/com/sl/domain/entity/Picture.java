package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sl_picture")
@ApiModel(value = "Picture", description = "图册")
public class Picture extends BaseEntity {

    @ApiModelProperty(value = "URL")
    private String url;
    @ApiModelProperty(value = "图册")
    private String albumCode;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlbumCode() {
        return albumCode;
    }

    public void setAlbumCode(String albumCode) {
        this.albumCode = albumCode;
    }
}