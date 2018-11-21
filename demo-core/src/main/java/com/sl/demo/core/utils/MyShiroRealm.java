package com.sl.demo.core.utils;

import com.sl.demo.core.common.AbstractUserService;
import com.sl.domain.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyShiroRealm extends AuthorizingRealm {
    //角色权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        AbstractUserService us = SpringUtils.getBean("userServiceImpl");
        User user = us.findByName(name);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }
}
