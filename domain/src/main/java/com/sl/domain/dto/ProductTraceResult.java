package com.sl.domain.dto;

import com.sl.domain.entity.ProductTraceHistory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

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

    @ApiModelProperty("扫描码")
    private String scanCode;

    private List<ProductTraceHistory> histories;

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

    public List<ProductTraceHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<ProductTraceHistory> histories) {
        this.histories = histories;
    }

    public String getScanCode() {
        return scanCode;
    }

    public void setScanCode(String scanCode) {
        this.scanCode = scanCode;
    }
}
