package com.sl.domain.entity.relation;

import com.sl.domain.entity.Menu;
import com.sl.domain.entity.NewsType;
import com.sl.domain.entity.Position;
import com.sl.domain.entity.Role;
import com.sl.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sl_menu_newstype")
@ApiModel(value = "MenuNewsType", description = "菜单新闻类型")
public class MenuNewsType extends BaseEntity {

    @ApiModelProperty(value = "菜单Code")
    private String menuCode;

    @ApiModelProperty(value = "新闻类型Code")
    private String newsTypeCode;

    @ApiModelProperty(value = "菜单")
    @Transient
    private Menu menu;

    @ApiModelProperty(value = "新闻类型")
    @Transient
    private NewsType newsType;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getNewsTypeCode() {
        return newsTypeCode;
    }

    public void setNewsTypeCode(String newsTypeCode) {
        this.newsTypeCode = newsTypeCode;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }
}
