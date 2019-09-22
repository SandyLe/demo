package com.sl.demo.server.controller;

import com.sl.demo.server.service.BrandService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Brand;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "BrandController", description = "品牌接口")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping(value = {"/brand/save"})
    public Result<Long> save(@RequestBody Brand brand){
        brandService.save(brand);
        return new Result<Long>(brand.getId());
    }
    @GetMapping(value = {"/brand/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = brandService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }
    @GetMapping(value = {"/brand/getOne"})
    public Result<Brand> getOne(Long id){
        Brand user = brandService.findById(id);
        return new Result<Brand> (user);
    }

    @PostMapping(value = {"/brand/delete"})
    public Result<Long[]> delete(Long[] id){
        brandService.delete(id);
        return new Result<Long[]> (id);
    }
}
