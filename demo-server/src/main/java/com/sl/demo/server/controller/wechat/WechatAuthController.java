package com.sl.demo.server.controller.wechat;

import com.sl.demo.core.common.AbstractUserService;
import com.sl.demo.core.utils.SpringUtils;
import com.sl.demo.server.service.WechatService;
import com.sl.domain.dto.util.Result;
import com.sl.domain.dto.wechat.AccessToken;
import com.sl.domain.dto.wechat.WechatAccountDto;
import com.sl.domain.entity.User;
import org.apache.commons.collections.EnumerationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ResponseBody
public class WechatAuthController {

    @Autowired
    private WechatService wechatService;

    @PostMapping(value = {"/wechat/auth"})
    public Result<AccessToken> auth(@RequestBody WechatAccountDto accountDto){
        AccessToken accessToken = wechatService.wechatLogin(accountDto.getCode());
        return new Result<AccessToken>(accessToken);
    }

    @PostMapping(value = {"/wechat/updateUserInfo"})
    public Result<String> updateUserInfo(@RequestBody WechatAccountDto accountDto){
        AbstractUserService us = SpringUtils.getBean("userServiceImpl");
        User user = us.findByWechatOpenId(accountDto.getOpenid());
        if(null == user){
            user = new User();
            user.setName(accountDto.getNickName());
            user.setGender(accountDto.getGender());
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setCreateUserId(0l);
            user.setUpdateUserId(0l);
            user.setNickName(accountDto.getNickName());
            user.setAvatarUrl(accountDto.getAvatarUrl());
            user.setWechatOpenId(accountDto.getOpenid());
            user.setRowSts(10);
            us.save(user);
        }
        return new Result<String>(user.getId());
    }
}
