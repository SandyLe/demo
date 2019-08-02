package com.sl.demo.server.util;

import com.sl.domain.dto.sys.LoginUser;
import com.sl.domain.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class LoginUtils {

    public static final String LOGIN_USER = "com.sl.demo.server.login.user";

    public static LoginUser getLoginUser(){
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getSession().getAttribute(LOGIN_USER);
        return  user;
    }

    public static Session getSession(){
        Session session = SecurityUtils.getSubject().getSession();
        return session;
    }

    public static String getSessionId(){
        return getSession().getId().toString();
    }
}
