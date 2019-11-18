package com.sl.demo.server.controller;

import com.sl.demo.server.service.IndexConfigService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.IndexConfig;
import com.sl.domain.enums.RowSts;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "IndexConfigController", description = "首页配置")
public class IndexConfigController {

    @Autowired
    private IndexConfigService indexConfigService;

    @GetMapping(value = {"/indexConfig/getOne"})
    public Result<IndexConfig> getOne(Long id){
        return new Result<IndexConfig>(indexConfigService.findById(id));
    }

    @PostMapping(value = {"/indexConfig/save"})
    public Result<Long> save(@RequestBody IndexConfig indexConfig){
        indexConfigService.save(indexConfig);
        return new Result<Long>(indexConfig.getId());
    }

    @GetMapping(value = {"/indexConfig/getPage"})
    public Result<Pagination> getPage(Pagination<IndexConfig> pagination){
        pagination = indexConfigService.findPage(pagination);
        return new Result<Pagination>(pagination);
    }

    @PostMapping(value = {"/indexConfig/delete"})
    public Result<Long[]> delete(Long[] ids){
        indexConfigService.delete(ids);
        return new Result<Long[]>(ids);
    }

    @GetMapping(value = {"/fc/indexConfig/getList"})
    public Result<List<IndexConfig>> getPage(Integer rowSts){
        List<IndexConfig> indexConfigs = indexConfigService.findList(rowSts == null ? RowSts.EFFECTIVE.getId() : rowSts);
        return new Result<List<IndexConfig>>(indexConfigs);
    }
}
