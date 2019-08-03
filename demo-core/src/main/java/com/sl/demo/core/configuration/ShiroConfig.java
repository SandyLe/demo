package com.sl.demo.core.configuration;

import com.sl.demo.core.security.shiro.MyAuthenticator;
import com.sl.demo.core.security.shiro.filter.FormAuthenticationFilter;
import com.sl.demo.core.security.shiro.filter.PermissionsAuthorizationFilter;
import com.sl.demo.core.utils.MyShiroRealm;
import com.sl.demo.core.utils.WechatShiroRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
@EnableCaching
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(CacheManager cacheManager){
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setAuthenticator(new MyAuthenticator());
        List<Realm> realms = new ArrayList<Realm>();
        realms.add(new MyShiroRealm());
        realms.add(new WechatShiroRealm());
        dwsm.setRealms(realms);
        //缓存配置

        return dwsm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("authc", new FormAuthenticationFilter());
        filters.put("perms", new PermissionsAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> chainMap = new LinkedHashMap<String, String>();
        chainMap.put("/login.jsp","anon");
        chainMap.put("/image/**", "anon");
        chainMap.put("/api/test/**", "anon");
        chainMap.put("/api/login", "anon");
        chainMap.put("/wechat/auth", "anon");
        chainMap.put("/**", "authc");
//        chainMap.put("/*", "perms");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chainMap);
        return shiroFilterFactoryBean;
    }
}
