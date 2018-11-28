package com.sl.demo.core.configuration;

import com.sl.demo.core.security.shiro.filter.FormAuthenticationFilter;
import com.sl.demo.core.security.shiro.filter.PermissionsAuthorizationFilter;
import com.sl.demo.core.utils.MyShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(CacheManager cacheManager){
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(new MyShiroRealm());
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
        chainMap.put("/image/**", "anon");
        chainMap.put("/test/**", "authc");
        chainMap.put("/*", "perms");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chainMap);
        return shiroFilterFactoryBean;
    }
}
