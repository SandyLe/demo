package com.sl.demo.server.controller;

import com.google.common.collect.Lists;
import com.sl.demo.core.utils.ImageUtil;
import com.sl.demo.server.service.AlbumService;
import com.sl.demo.server.service.PictureService;
import com.sl.demo.server.util.LoginUtils;
import com.sl.domain.dto.PicDto;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Album;
import com.sl.domain.entity.Picture;
import com.sl.domain.enums.RowSts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Api(value = "TestController", description = "测试接口")
public class PicController {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private AlbumService albumService;
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.webPath}")
    private String webPath;

    @ApiOperation(value = "图片上传",notes = "图片上传")
    @PostMapping(value = {"/pic/upload"})
    public String upload(@RequestParam(required = true, value = "multipartFiles") MultipartFile[] multipartFiles, @RequestParam(required = false, value = "albumCode")String albumCode) {

        File path = new File(uploadPath + albumCode + File.separator);
        if(!path.exists()){
            path.mkdir();
        }
        if(null != multipartFiles && multipartFiles.length > 0){
            List<String> files = new ArrayList<>();
            try{
                for(MultipartFile multipartFile : multipartFiles){
                    String endFix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf("."));
                    String fileName = System.currentTimeMillis() + String.valueOf(new Random().nextInt()) + endFix;
                    File file = new File(uploadPath + albumCode + File.separator + fileName);
                    multipartFile.transferTo(file);
                    files.add(file.getAbsolutePath());

                    Picture picture = new Picture();
                    picture.setAlbumCode(albumCode);
                    picture.setUrl( webPath + albumCode + File.separator + fileName);
                    picture.setRowSts(RowSts.EFFECTIVE.getId());
                    picture.setCreateDate(new Date());
                    picture.setUpdateDate(new Date());
                    picture.setCreateUserId(LoginUtils.getLoginUser().getId());
                    pictureService.save(picture);
                }
                ImageUtil.generateThumbnail2Directory(uploadPath + albumCode + File.separator, files.toArray(new String[]{}));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "OK";
    }


    @ApiOperation(value = "图片列表",notes = "图片列表")
    @GetMapping(value = {"/pic/getList"})
    public Result<List<PicDto>> getPicList(@RequestParam(value = "albumCode", required = false) String albumCode){
        Result<List<PicDto>> result = new Result<List<PicDto>>();
        List<PicDto> dtoList = Lists.newArrayList();
        List<Picture> dataList = pictureService.findList(RowSts.EFFECTIVE.getId());
        Map<String, List<Picture>> map = dataList.stream().collect(Collectors.groupingBy(Picture::getAlbumCode));
        List<String> albumList = Lists.newArrayList(map.keySet());
        List<Album> albums = albumService.findByCodes(albumList);
        Map<String, Album> albumMap = albums.stream().collect(Collectors.toMap(x->x.getCode(),x->x));
        for(Map.Entry<String,List<Picture>> entry : map.entrySet()){
            PicDto dto = new PicDto();
            dto.setAlbum(albumMap.get(entry.getKey()));
            List<String> urls = entry.getValue().stream().map(x->x.getUrl().replace(".","-thumbnail.")).collect(Collectors.toList());
            dto.setUrls(urls);
            dtoList.add(dto);
        }
        result.setData(dtoList);
        return result;
    }
    @ApiOperation(value = "根据图册获取图片",notes = "根据图册获取图片")
    @GetMapping(value = {"/fc/pic/getList"})
    public Result<Set<String>> getPics(@RequestParam(value = "albumCode", required = false) String albumCode,
                                        @RequestParam(value = "limit", required = false) Integer limit){
        List<Picture> pictures = pictureService.findList(RowSts.EFFECTIVE.getId(), albumCode);
        if(null != limit && pictures.size() > limit){
            pictures = pictures.subList(0, limit);
        }
        Set<String> urls = pictures.stream().collect(Collectors.toMap(o->o.getUrl(), o->o)).keySet();
        return new Result<Set<String>>(urls);
    }
}