package com.sl.demo.server.controller;

import com.sl.demo.server.service.EmailService;
import com.sl.demo.server.util.FileUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@Api(value = "CooprationController", description = "商务合作")
public class CooprationController {

    @Autowired
    private EmailService emailService;
    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/fc/cooperation/sendEmail")
    @ResponseBody
    public boolean sendEmail(@RequestParam("file") MultipartFile[] file, String to, String subject, String contentText){
        if(file.length>0) {
            List list = new ArrayList();
            for(int i=0;file.length>i;i++) {
                // 获取文件名称,包含后缀
                String fileName = file[i].getOriginalFilename();
                long t = System.currentTimeMillis();// 获得当前系统毫秒数,
                fileName = t + fileName;
                FileUtil.uploadFile(file[i], uploadPath, fileName);
                list.add(uploadPath + fileName);
            }
            return  emailService.sendAttachmentMail(to,subject,contentText,list);
        }
        return  emailService.sendAttachmentMail(to,subject,contentText);
    }

    @PostMapping("/fc/cooperation/sendEmailNofile")
    @ResponseBody
    public boolean sendEmail(String to, String subject, String contentText){

        return  emailService.sendAttachmentMail(to,subject,contentText);
    }
}
