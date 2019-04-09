package com.carshop.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 解决前后端跨域问题
 * @author lhy
 * @create 2018-05-27-18:06
 * 什么是同一个域？
 * 同一协议，同一ip，同一端口，三同中有一不同就产生了跨域。
 **/
@Configuration
public class CrosConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("*")
                .exposedHeaders("Token")
                .maxAge(3600);
    }

}
