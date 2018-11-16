package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sl_user")
@ApiModel(value = "User", description = "用户")
public class User extends BaseEntity {

}
