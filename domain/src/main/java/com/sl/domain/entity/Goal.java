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
public class Goal extends BaseEntity {

    @ApiModelProperty(value = "备注")
    private String text;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "截止日期")
    private Date deadLine;

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
}
