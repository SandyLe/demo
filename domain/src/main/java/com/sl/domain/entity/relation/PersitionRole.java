package com.sl.domain.entity.relation;

import com.sl.domain.entity.Position;
import com.sl.domain.entity.Role;
import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PersitionRole", description = "岗位角色")
public class PersitionRole extends BaseEntity {

    @ApiModelProperty(value = "岗位")
    private Position position;

    @ApiModelProperty(value = "角色")
    private Role role;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
