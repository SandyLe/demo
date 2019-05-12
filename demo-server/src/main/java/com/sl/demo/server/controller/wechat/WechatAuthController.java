package com.sl.demo.server.controller.wechat;

import com.sl.demo.server.service.WechatService;
import com.sl.domain.dto.util.Result;
import com.sl.domain.dto.wechat.AccessToken;
import com.sl.domain.dto.wechat.WechatAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

        return new Result<String>("");
    }
}
