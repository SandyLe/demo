package com.sl.demo.server.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import com.sl.demo.core.common.AbstractUserService;
import com.sl.demo.core.utils.SpringUtils;
import com.sl.demo.server.service.WechatService;
import com.sl.domain.dto.util.Result;
import com.sl.domain.dto.wechat.AccessToken;
import com.sl.domain.dto.wechat.WechatAccountDto;
import com.sl.domain.entity.User;
import org.apache.commons.collections.EnumerationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseBody
public class WechatAuthController {

    @Autowired
    private WechatService wechatService;

    /**
     * 获取token
     * @Since 2020-12-19 15:56:00
     * @auth sandylee
     * @param code
     * @param request
     * @return
     */
    @GetMapping(value = {"/wechat/getToken"})
    public Result<Map<String, Object>> getToken(String code, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        String sessionid = request.getSession().getId();
        JSONObject wechatObj = wechatService.getOpenIdMsg(code);
        if (null != wechatObj) {
            map.put("openid", wechatObj.getString("openid"));
        }
        map.put("session_key", sessionid);

        return new Result<Map<String, Object>>(map);
    }

    /**
     * 登录
     * @Since 2020-12-19 15:56:00
     * @auth sandylee
     * @param accountDto
     * @return
     */
    @PostMapping(value = {"/wechat/login"})
    public Result<Map<String, Object>> login(@RequestBody WechatAccountDto accountDto){

        Map<String, Object> map = wechatService.wechatLogin(accountDto);

        return new Result<Map<String, Object>>(map);
    }

    /**
     * 注册
     * @Since 2020-12-19 15:56:00
     * @auth sandylee
     * @param accountDto
     * @return
     */
    @PostMapping(value = {"/wechat/register"})
    public Result<String> register(@RequestBody WechatAccountDto accountDto){
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
