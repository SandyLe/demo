package com.sl.domain.dto;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.News;
import io.swagger.annotations.ApiModelProperty;

public class NewsPagination extends Pagination<News> {

    @ApiModelProperty("新闻类型")
    private String newsTypeCode;

    public String getNewsTypeCode() {
        return newsTypeCode;
    }

    public void setNewsTypeCode(String newsTypeCode) {
        this.newsTypeCode = newsTypeCode;
    }
}
