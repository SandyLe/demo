package com.sl.demo.server.controller;

import com.sl.demo.server.service.AlbumService;
import com.sl.demo.server.util.LoginUtils;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Album;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Api(value = "AlbumController", description = "图册管理")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping(value = {"/album/save"})
    public Result<Long> save(@RequestBody Album album){
        if(null == album.getId()){
            album.setCreateUserId(LoginUtils.getLoginUser().getId());
            album.setCreateDate(new Date());
        }
        album.setUpdateUserId(LoginUtils.getLoginUser().getId());
        album.setUpdateDate(new Date());
        albumService.save(album);
        return new Result<Long>(album.getId());
    }

    @GetMapping(value = {"/album/getOne"})
    public Result<Album> getOne(@RequestParam(value = "id") Long id){
        Album album = albumService.findById(id);
        return new Result<Album>(album);
    }

    @GetMapping(value = {"/album/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = albumService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }

    @PostMapping(value = {"/album/delete"})
    public Result<Long[]> delete(Long[] id){
        albumService.delete(id);
        return new Result<Long[]> (id);
    }
}
