package com.sl.demo.server.service;

import com.sl.domain.dto.MenuDto;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Menu;

import java.util.List;

public interface MenuService {
    void save(Menu menu);
    Pagination<Menu> findPage(Pagination<Menu> pagination);
    Menu findById(Long id);
    void delete(Long[] id);
    List<Menu> findList(String level, String parent);
    List<Menu> findByCodes(List<String> codeList);
    List<MenuDto> findFcList();
}
