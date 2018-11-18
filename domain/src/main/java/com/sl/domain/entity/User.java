package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javafx.geometry.Pos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sl_user")
@ApiModel(value = "User", description = "用户")
public class User extends BaseEntity {

    @ManyToMany
    @JoinTable(name = "user_position", joinColumns = @JoinColumn( name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "position_id"))
    @ApiModelProperty(value = "岗位")
    private List<Position> positions;

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
