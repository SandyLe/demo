package com.sl.demo.server;

import com.sl.demo.core.configuration.Swagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ImportAutoConfiguration({Swagger2.class})
@EntityScan(basePackages = {"com.sl.domain.entity"})
public class DemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoServerApplication.class, args);
    }
}
