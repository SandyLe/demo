package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.relation.MenuNewsType;

import java.util.List;

public interface MenuNewsTypeService {
    void save(MenuNewsType menuNewsType);
    Pagination<MenuNewsType> findPage(Pagination<MenuNewsType> pagination);
    MenuNewsType findById(Long id);
    void delete(Long[] id);
    List<String> getPath(String newsType);
}
