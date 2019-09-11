package com.sl.domain.entity;

import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sl_news_type")
@ApiModel(value = "NewsType", description = "新闻类型")
public class NewsType extends BaseEntity {
}