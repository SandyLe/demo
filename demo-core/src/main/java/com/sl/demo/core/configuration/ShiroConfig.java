package com.sl.demo.core.configuration;

import com.sl.demo.core.utils.MyShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//        shiroFilterFactoryBean.setFilters();
        return shiroFilterFactoryBean;
    }
}
