package com.sl.demo.server;

import com.sl.demo.core.configuration.CoreConfig;
import com.sl.demo.core.configuration.ShiroConfig;
import com.sl.demo.core.configuration.Swagger2;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ImportAutoConfiguration({Swagger2.class, CoreConfig.class, ShiroConfig.class})
@EntityScan(basePackages = {"com.sl.domain.entity"})
public class DemoServerApplication {

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        //springboot版本不同可能下面的类名会不同或者类的包路径会不同
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //监听http的端口号
        connector.setPort(80);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号ss
        System.out.println("监听到了80端口");
        connector.setRedirectPort(443);//这里的端口写成和配置文件一样的端口就Ok
        return connector;
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoServerApplication.class, args);
    }
}
