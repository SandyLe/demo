package com.sl.demo.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sl.demo.core.common.AbstractUserService;
import com.sl.demo.core.utils.SpringUtils;
import com.sl.demo.server.service.WechatService;
import com.sl.domain.dto.wechat.AccessToken;
import com.sl.domain.dto.wechat.WechatAccountDto;
import com.sl.domain.dto.wechat.WechatOpenidToken;
import com.sl.domain.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WechatServiceImpl implements WechatService {


    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.grantType}")
    private String grantType;
    @Value("${wechat.sessionHost}")
    private String sessionHost;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject getOpenIdMsg (String code ) {
        String result = restTemplate.getForObject(getSessionHost() + "?appid={appid}&secret={srcret}&js_code={code}&grant_type={grantType}" , String.class,getAppId(),getSecret(),code, getGrantType());
        JSONObject json = JSON.parseObject(result);
        if(null == json.get("errcode")){
            return json;
        }
        return null;
    }

    @Override
    public Map<String, Object> wechatLogin (WechatAccountDto accountDto) {

        WechatOpenidToken token = new WechatOpenidToken(accountDto.getOpenid(), accountDto.getSession_key(), true, "wx");
        Subject subject = SecurityUtils.getSubject();

        AbstractUserService us = SpringUtils.getBean("userServiceImpl");
        User user = us.findByWechatOpenId(accountDto.getOpenid());
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == user) {
            //新客户，需要新增注册
            map.put("result","2");
        } else {
            try
            {
                subject.login(token);
                map.put("result","1");
            }
            catch (AuthenticationException e)
            {
                map.put("result","0");
                map.put("msg","请使用微信登录平台");
            }
        }
        return map;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getSessionHost() {
        return sessionHost;
    }

    public void setSessionHost(String sessionHost) {
        this.sessionHost = sessionHost;
    }
}
