package com.sl.demo.server.controller;

import com.sl.demo.server.service.NewsTypeService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.NewsType;
import com.sl.domain.enums.RowSts;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "NewsTypeController", description = "文章类型接口")
public class NewsTypeController {

    @Autowired
    private NewsTypeService newsTypeService;

    @PostMapping(value = {"/newsType/save"})
    public Result<Long> save(@RequestBody NewsType newsType){
        newsTypeService.save(newsType);
        return new Result<Long>(newsType.getId());
    }
    @GetMapping(value = {"/newsType/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = newsTypeService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }
    @GetMapping(value = {"/newsType/getOne"})
    public Result<NewsType> getOne(Long id){
        NewsType newsType = newsTypeService.findById(id);
        return new Result<NewsType> (newsType);
    }
    @PostMapping(value = {"/newsType/delete"})
    public Result<Long[]> delete(Long[] id){
        newsTypeService.delete(id);
        return new Result<Long[]> (id);
    }

    @GetMapping(value = {"/newsType/getList"})
    public Result<List<NewsType>> getList(@RequestParam(value = "rowSts", required = false)Integer rowSts){
        rowSts = null == rowSts ? RowSts.EFFECTIVE.getId() : rowSts;
        List<NewsType> newsTypeList = newsTypeService.findList(null, rowSts);
        return new Result<List<NewsType>> (newsTypeList);
    }
}
