package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sl_product_type")
@ApiModel(value = "ProductType", description = "产品类型")
public class ProductType extends BaseEntity {

}
