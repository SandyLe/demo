package com.sl.demo.server.controller;

import com.sl.demo.server.service.FeedbackService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Feedback;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Api(value = "FeedbackController", description = "产品溯源验证接口")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Value("${upload.path}")
    private String filePath;
    @Value("${upload.webPath}")
    private String webPath;

    @RequestMapping(value = {"/fc/feedback/save"}, method = RequestMethod.POST)
    public Result<Long> save(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
                             @RequestParam(value = "contact") String contact, @RequestParam("email") String email, @RequestParam("content") String content){
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        // 获取文件存放地址
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();// 不存在路径则进行创建
        }
        FileOutputStream out = null;
        try {
            // 重新自定义文件的名称
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String d = sdf.format(date);// 时间
            filePath = filePath + d + fileName;
            out = new FileOutputStream(filePath);
            out.write(file.getBytes());
            out.flush();
            out.close();

            Feedback feedback = new Feedback();
            feedback.setContact(contact);
            feedback.setEmail(email);
            feedback.setPicUrl(webPath + d + fileName);
            feedback.setName(name);
            feedback.setDescription(content);

            feedbackService.save(feedback);
            return new Result<Long>(feedback.getId());

        } catch (Exception e) {
            return new Result<Long>(-1l);
        }
    }

    @GetMapping(value = {"/feedback/getPage"})
    public Result<Pagination> getPage(Pagination pagination){

        pagination = feedbackService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }

    @GetMapping(value = {"/feedback/getOne"})
    public Result<Feedback> getOne(Long id){

        Feedback Feedback = feedbackService.findById(id);
        return new Result<Feedback> (Feedback);
    }

    @PostMapping(value = {"/feedback/delete"})
    public Result<Long[]> delete(Long[] id){

        feedbackService.delete(id);
        return new Result<Long[]> (id);
    }

}
