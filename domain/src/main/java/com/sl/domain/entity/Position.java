package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sl_position")
@ApiModel(value = "Position", description = "岗位")
public class Position extends BaseEntity {

    @ManyToMany(mappedBy ="positions")
    @ApiModelProperty(value = "用户")
    private List<User> users;

    @ManyToMany
    @JoinTable(name = "position_role", joinColumns = @JoinColumn( name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ApiModelProperty(value = "角色")
    private List<Role> roles;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
