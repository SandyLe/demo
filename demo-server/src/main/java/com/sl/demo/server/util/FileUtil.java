package com.sl.demo.server.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Random;

/**
 * @author
 * @desc
 * @date 2021/11/28 10:45 AM
 */
public class FileUtil {

    /**
     * 上传文件
     * @param multipartFile
     * @param uploadPath
     * @return
     */
    public static String uploadFile (MultipartFile multipartFile, String uploadPath, String fileName) {

        try{
            String endFix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf("."));
            if (!StringUtils.hasText(fileName)) {
                fileName = System.currentTimeMillis() + String.valueOf(new Random().nextInt()) + endFix;
            }
            String filePath = uploadPath + fileName;
            File file = new File(filePath);
            multipartFile.transferTo(file);
        }catch (Exception e) {
            System.out.println("文件上传异常：" + e.getMessage());
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 文件上传
     * @param multipartFile
     * @param uploadPath
     * @return
     */
    public static String uploadFile (MultipartFile multipartFile, String uploadPath) {

        return uploadFile (multipartFile, uploadPath, null);
    }
}
