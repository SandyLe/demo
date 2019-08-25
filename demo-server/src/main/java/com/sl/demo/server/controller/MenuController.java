package com.sl.demo.server.controller;

import com.sl.demo.server.service.MenuService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Menu;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "MenuController", description = "菜单接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping(value = {"/menu/save"})
    public Result<Long> save(@RequestBody Menu menu){
        menuService.save(menu);
        return new Result<Long>(menu.getId());
    }
    @GetMapping(value = {"/menu/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = menuService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }
    @GetMapping(value = {"/menu/getList"})
    public Result<List<Menu>> getPage(@RequestParam(value = "level", required = false) String level,
                                      @RequestParam(value = "parent", required = false) String parent){
        List<Menu> menuList = menuService.findList(level, parent);
        return new Result<List<Menu>> (menuList);
    }
    @GetMapping(value = {"/menu/getOne"})
    public Result<Menu> getOne(Long id){
        Menu menu = menuService.findById(id);
        return new Result<Menu> (menu);
    }

    @PostMapping(value = {"/menu/delete"})
    public Result<Long[]> delete(Long[] id){
        menuService.delete(id);
        return new Result<Long[]> (id);
    }
}
