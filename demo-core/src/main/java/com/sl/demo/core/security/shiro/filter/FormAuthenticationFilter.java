package com.sl.demo.core.security.shiro.filter;

import com.sl.domain.dto.wechat.WechatOpenidToken;
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
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("access-token");
        if(null != token && !"".equals(token)){
            return true;
        }
        return super.isLoginRequest(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(isLoginRequest(request, response)){
            WechatOpenidToken token = new WechatOpenidToken(((HttpServletRequest)request).getHeader("access-token"));
            getSubject(request,response).login(token);
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
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
