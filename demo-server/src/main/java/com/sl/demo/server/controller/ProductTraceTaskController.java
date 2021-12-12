package com.sl.demo.server.controller;

import com.sl.demo.server.service.ProductTraceTaskService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.ProductTraceTask;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "ProductTraceTaskController", description = "产品溯源码接口")
public class ProductTraceTaskController {

    @Autowired
    private ProductTraceTaskService productTraceTaskService;

    @PostMapping(value = {"/productTraceTask/save"})
    public Result<Long> save(@RequestBody ProductTraceTask productTraceTask){
        productTraceTaskService.save(productTraceTask);
        return new Result<Long>(productTraceTask.getId());
    }

    @GetMapping(value = {"/productTraceTask/getPage"})
    public Result<Pagination> getPage(Pagination pagination){

        pagination = productTraceTaskService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }

    @GetMapping(value = {"/productTraceTask/getOne"})
    public Result<ProductTraceTask> getOne(Long id){

        ProductTraceTask productTraceTask = productTraceTaskService.findById(id);
        return new Result<ProductTraceTask> (productTraceTask);
    }

    @PostMapping(value = {"/productTraceTask/delete"})
    public Result<Long[]> delete(Long[] id){

        productTraceTaskService.delete(id);
        return new Result<Long[]> (id);
    }

    @PostMapping(value = {"/productTraceTask/genTraceCode"})
    public Result<Long[]> genTraceCode(Long[] id){

        productTraceTaskService.genTraceCode(id);
        return new Result<Long[]> (id);
    }
}
