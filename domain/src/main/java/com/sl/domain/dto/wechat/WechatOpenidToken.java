package com.sl.domain.dto.wechat;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.util.StringUtils;

public class WechatOpenidToken extends UsernamePasswordToken {

    private String openId;

    public WechatOpenidToken(String openId) {
        super();
        this.openId = openId;
    }

    public WechatOpenidToken(String openId, String pwd, Boolean remeberMe, String host) {
        super();
        this.setOpenId(openId);
        if (StringUtils.hasText(pwd)) {
            this.setPassword(pwd.toCharArray());
        }
        this.setUsername(openId);
        this.setRememberMe(remeberMe);
        this.setHost(host);
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
    public Object getPrincipal() {
        return getOpenId();
    }

}
