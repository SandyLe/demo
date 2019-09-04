package com.sl.demo.server.controller;

import com.sl.demo.server.service.PositionService;
import com.sl.demo.server.service.UserService;
import com.sl.domain.entity.Position;
import com.sl.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "TestController", description = "测试接口")
public class PicController {

    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;
    @Value("${upload.path}")
    private String uploadPath;

    @ApiOperation(value = "测试",notes = "Test")
    @PostMapping(value = {"/pic/upload"})
    public String upload(@RequestParam(required = true, value = "multipartFiles") MultipartFile[] multipartFiles, @RequestParam(required = false, value = "albumCode")String albumCode) {

        File path = new File(uploadPath);
        if(!path.exists()){
            path.mkdir();
        }
        if(null != multipartFiles && multipartFiles.length > 0){
            for(MultipartFile multipartFile : multipartFiles){
                File file = new File(uploadPath + multipartFile.getOriginalFilename());
                try{
                    multipartFile.transferTo(file);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return "hello word1!";
    }
}
