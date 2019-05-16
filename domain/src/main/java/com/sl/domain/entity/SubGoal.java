package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sl_goal")
@ApiModel(value = "Goal", description = "操作权限")
public class SubGoal extends BaseEntity {

    @ApiModelProperty(value = "步骤")
    private Integer index;
    @ApiModelProperty(value = "备注")
    private String text;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "截止日期")
    private Date deadLine;
    @ApiModelProperty(value = "主表ID")
    private Integer parentid;
    @ApiModelProperty(value = "分值")
    private Double score;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Integer getParentid() {
        return parentid;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
