package com.sl.demo.server.controller;

import com.sl.demo.server.service.MenuNewsTypeService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Brand;
import com.sl.domain.entity.relation.MenuNewsType;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "BrandController", description = "菜单文章类型接口")
public class MenuNewsTypeController {

    @Autowired
    private MenuNewsTypeService menuNewsTypeService;

    @PostMapping(value = {"/menuNewsType/save"})
    public Result<Long> save(@RequestBody MenuNewsType menuNewsType){
        menuNewsTypeService.save(menuNewsType);
        return new Result<Long>(menuNewsType.getId());
    }
    @GetMapping(value = {"/menuNewsType/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = menuNewsTypeService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }
    @GetMapping(value = {"/menuNewsType/getOne"})
    public Result<MenuNewsType> getOne(Long id){
        MenuNewsType menuNewsType = menuNewsTypeService.findById(id);
        return new Result<MenuNewsType> (menuNewsType);
    }

    @PostMapping(value = {"/menuNewsType/delete"})
    public Result<Long[]> delete(Long[] id){
        menuNewsTypeService.delete(id);
        return new Result<Long[]> (id);
    }
}
