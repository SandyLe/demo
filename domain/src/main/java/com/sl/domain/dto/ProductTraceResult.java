package com.sl.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "产品防伪查询DTO")
public class ProductTraceResult {

    @ApiModelProperty("成功标识")
    private Integer resultFlag;

    @ApiModelProperty("备注")
    private String msg;

    public Integer getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(Integer resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
