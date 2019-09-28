package com.sl.demo.server.controller;

import com.sl.demo.server.service.HotProductService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.HotProduct;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "HotProductController", description = "热销产品接口")
public class HotProductController {

    @Autowired
    private HotProductService hotProductService;

    @PostMapping(value = {"/hotProduct/save"})
    public Result<Long> save(@RequestBody HotProduct hotProduct){
        hotProductService.save(hotProduct);
        return new Result<Long>(hotProduct.getId());
    }
    @GetMapping(value = {"/hotProduct/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = hotProductService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }
    @GetMapping(value = {"/hotProduct/getOne"})
    public Result<HotProduct> getOne(Long id){
        HotProduct hotProduct = hotProductService.findById(id);
        return new Result<HotProduct> (hotProduct);
    }

    @PostMapping(value = {"/hotProduct/delete"})
    public Result<Long[]> delete(Long[] id){
        hotProductService.delete(id);
        return new Result<Long[]> (id);
    }
}
