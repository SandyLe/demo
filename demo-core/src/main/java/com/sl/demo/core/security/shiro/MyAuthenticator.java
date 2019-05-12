package com.sl.demo.core.security.shiro;

import com.sl.domain.dto.wechat.WechatOpenidToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

public class MyAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {

        //判空
        assertRealmsConfigured();

        Collection<Realm> realms = getRealms();
        if(realms.size()==1){
            return doSingleRealmAuthentication(realms.iterator().next(), authenticationToken);
        }else{
            return doMultiRealmAuthentication(realms, authenticationToken);
        }
    }
}
