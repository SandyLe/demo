package com.sl.demo.server.controller;

import com.sl.demo.server.service.NewsService;
import com.sl.domain.dto.NewsPagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.News;
import com.sl.domain.enums.RowSts;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "NewsController", description = "文章接口")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping(value = {"/news/save"})
    public Result<Long> save(@RequestBody News news){
        newsService.save(news);
        return new Result<Long>(news.getId());
    }
    @GetMapping(value = {"/news/getPage"})
    public Result<NewsPagination> getPage(NewsPagination pagination){
        pagination = newsService.findPage(pagination);
        return new Result<NewsPagination> (pagination);
    }
    @GetMapping(value = {"/news/getOne"})
    public Result<News> getOne(Long id){
        News news = newsService.findById(id);
        return new Result<News> (news);
    }

    @PostMapping(value = {"/news/delete"})
    public Result<Long[]> delete(Long[] id){
        newsService.delete(id);
        return new Result<Long[]> (id);
    }

    @PostMapping(value = {"/news/enable"})
    public Result<Long> enable(Long id){
        newsService.updateSts(id, RowSts.EFFECTIVE.getId());
        return new Result<Long> (id);
    }

    @PostMapping(value = {"/news/disable"})
    public Result<Long> disable(Long id){
        newsService.updateSts(id, RowSts.DISABLE.getId());
        return new Result<Long> (id);
    }
}
