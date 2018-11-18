package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sl_role")
@ApiModel(value = "Role", description = "角色")
public class Role extends BaseEntity {

    @ManyToMany(mappedBy = "roles")
    @ApiModelProperty(value = "岗位")
    private List<Position> positions;

    @ManyToMany
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn( name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
