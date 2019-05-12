package com.sl.domain.enums;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Gender", description = "枚举：性别")
public enum  Gender {

    UNKNOW(0,"unknow"),//未知
    MALE(1,"male"),//男
    FEMALE(2,"female");//女
    private Integer id;
    private String name;

    Gender(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
