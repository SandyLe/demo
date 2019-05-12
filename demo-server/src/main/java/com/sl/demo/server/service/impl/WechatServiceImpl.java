package com.sl.demo.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sl.demo.server.service.WechatService;
import com.sl.domain.dto.wechat.AccessToken;
import com.sl.domain.dto.wechat.WechatOpenidToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public AccessToken wechatLogin(String code) {
        String result = restTemplate.getForObject(getSessionHost() + "?appid={appid}&secret={srcret}&js_code={code}&grant_type={grantType}" , String.class,getAppId(),getSecret(),code, getGrantType());
        JSONObject json = JSON.parseObject(result);
        AccessToken accessToken = new AccessToken();
        if(null == json.get("errcode")){
            accessToken.setToken(json.getString("session_key"));
            accessToken.setOpenid(json.getString("openid"));

            WechatOpenidToken wechatOpenidToken = new WechatOpenidToken(accessToken.getOpenid());
            Subject subject = SecurityUtils.getSubject();
            subject.login(wechatOpenidToken);
        } else {
            System.out.println(result);
        }
        return accessToken;
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
