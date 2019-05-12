package com.sl.demo.core.security.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FormAuthenticationFilter extends AuthenticatingFilter {
    public static final String DEFAULT_USERNAME = "username";
    public static final String DEFAULT_PASSWORD = "password";
    public static final String DEFAULT_REMEMBERME = "rememberMe";

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        return createToken(WebUtils.getCleanParam(request, DEFAULT_USERNAME), WebUtils.getCleanParam(request, DEFAULT_PASSWORD), request,response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request,response);
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        System.out.println(servletRequest.getServletPath());
        if(subject.getPrincipal() == null){
            WebUtils.issueRedirect(request,response,"/login");
        } else {
            System.out.println("没权限");
        }
        return false;
    }

}
