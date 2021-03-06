package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.MenuNewsTypeRepository;
import com.sl.demo.server.service.MenuNewsTypeService;
import com.sl.demo.server.service.MenuService;
import com.sl.demo.server.service.NewsTypeService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Menu;
import com.sl.domain.entity.NewsType;
import com.sl.domain.entity.relation.MenuNewsType;
import com.sl.domain.enums.RowSts;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuNewsTypeServiceImpl implements MenuNewsTypeService {

    @Autowired
    private MenuNewsTypeRepository menuNewsTypeRepository;
    @Autowired
    private MenuService menuService;
    @Autowired
    private NewsTypeService newsTypeService;

    @Override
    public void save(MenuNewsType menuNewsType) {
        if(null == menuNewsType.getId()){
            menuNewsType.setRowSts(10);
            menuNewsType.setCreateDate(new Date());
        }
        menuNewsTypeRepository.save(menuNewsType);
    }

    @Override
    public Pagination<MenuNewsType> findPage(Pagination<MenuNewsType> pagination) {
        Page<MenuNewsType> page = menuNewsTypeRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        List<MenuNewsType> menuNewsTypes = page.getContent();
        List<String> menuCodeList = Lists.newArrayList();
        List<String> newsTypeCodeList = Lists.newArrayList();
        for (MenuNewsType menuNewsType : menuNewsTypes) {
            menuCodeList.add(menuNewsType.getMenuCode());
            newsTypeCodeList.add(menuNewsType.getNewsTypeCode());
        }
        List<Menu> menuList = menuService.findByCodes(menuCodeList);
        List<NewsType> newsTypeList = newsTypeService.findList(newsTypeCodeList, RowSts.EFFECTIVE.getId());
        Map<String, Menu> menuMap = menuList.stream().collect(Collectors.toMap(Menu::getCode,item->item));
        Map<String, NewsType> newsTypeMap = newsTypeList.stream().collect(Collectors.toMap(NewsType::getCode,item->item));
        menuNewsTypes.stream().forEach(o->{
            o.setMenu(menuMap.get(o.getMenuCode()));
            o.setNewsType(newsTypeMap.get(o.getNewsTypeCode()));
        });
        pagination.setData(page.getContent());
        return pagination;
    }

    @Override
    public MenuNewsType findById(Long id) {
        return menuNewsTypeRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            menuNewsTypeRepository.delete(tempId);
        }
    }

    @Override
    public List<String> getPath(String newsType) {
        List<String> menus = Lists.newArrayList();
        MenuNewsType menuNewsType = menuNewsTypeRepository.findByNewsType(newsType);
        if(null != menuNewsType){
            List<Menu> menuList = menuService.findByCodes(Lists.newArrayList(menuNewsType.getMenuCode()));
            if(CollectionUtils.isNotEmpty(menuList)){
                menus.add(menuList.get(0).getName());
                getParentMenus(menuList.get(0), menus);
            }
        }
        Collections.reverse(menus);
        return menus;
    }

    private List<String> getParentMenus (Menu menu, List<String> menuNames){
        if(null != menu && StringUtils.hasText(menu.getParent())){
            List<Menu> menus = menuService.findByCodes(Lists.newArrayList(menu.getParent()));
            if(CollectionUtils.isNotEmpty(menus)){
                menuNames.add(menus.get(0).getName());
                getParentMenus(menus.get(0), menuNames);
            }
        }
        return menuNames;
    }
}
