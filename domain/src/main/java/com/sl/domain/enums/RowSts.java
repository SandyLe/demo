package com.sl.domain.enums;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "RowSts", description = "枚举：状态")
public enum RowSts {

    NEW(5,"new"),//新增
    EFFECTIVE(10,"effective"),//有效
    DISABLE(20,"disable"),//有效
    DELETE(0,"delete");//删除
    private Integer id;
    private String name;

    RowSts(Integer id, String name) {
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
