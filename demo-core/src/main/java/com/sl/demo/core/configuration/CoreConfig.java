package com.sl.demo.core.configuration;

import com.sl.demo.core.utils.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreConfig {
    @Bean
    static SpringUtils springUtils(){
        return new SpringUtils();
    }
    @Bean
    static RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
