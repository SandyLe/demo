package com.sl.domain.dto.wechat;

import org.apache.shiro.authc.UsernamePasswordToken;

public class WechatOpenidToken extends UsernamePasswordToken {

    private String openId;

    public WechatOpenidToken(String openId) {
        super();
        this.openId = openId;
    }
    public  WechatOpenidToken(){
        super();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public Object getCredentials() {
        return "ok";
    }
}
