package com.sl.demo.core.configuration;

import com.sl.demo.core.utils.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {
    @Bean
    static SpringUtils springUtils(){
        return new SpringUtils();
    }
}
