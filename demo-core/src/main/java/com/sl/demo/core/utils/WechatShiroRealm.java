package com.sl.demo.core.utils;

import com.sl.demo.core.common.AbstractUserService;
import com.sl.domain.dto.wechat.WechatOpenidToken;
import com.sl.domain.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class WechatShiroRealm extends AuthorizingRealm {
    //角色权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        WechatOpenidToken token = (WechatOpenidToken) authenticationToken;
        String openid = token.getOpenId();
        System.out.println(openid);
        AbstractUserService us = SpringUtils.getBean("userServiceImpl");
        User user = us.findByWechatOpenId(openid);

        AuthenticationInfo info = new SimpleAuthenticationInfo(openid, "ok", this.getClass().getSimpleName());
        return info;
    }
}
