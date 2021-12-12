package com.sl.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "产品防伪查询DTO")
public class ProductTraceResult {

    @ApiModelProperty("成功标识")
    private Integer resultFlag;

    @ApiModelProperty("备注")
    private String msg;

    @ApiModelProperty("首次查询时间")
    private Date firstQueryDate;

    @ApiModelProperty("查询历史ID")
    private Long historyId;

    @ApiModelProperty("查询历史ID")
    private String firstAdd;

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

    public Date getFirstQueryDate() {
        return firstQueryDate;
    }

    public void setFirstQueryDate(Date firstQueryDate) {
        this.firstQueryDate = firstQueryDate;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public String getFirstAdd() {
        return firstAdd;
    }

    public void setFirstAdd(String firstAdd) {
        this.firstAdd = firstAdd;
    }
}
