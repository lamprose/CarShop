package com.carshop.server.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class Configation extends WebMvcConfigurerAdapter {

    @Bean
    public HandlerInterceptor getInterceptor(){
        return new Interceptor();
    }

    //注册拦截器2
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         /*addPathPatterns 用于添加拦截规则
         excludePathPatterns 用户排除拦截*/
        registry.addInterceptor(getInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/register", "/", "/user/login","/UserAvatar/**","/user/getInfo");
    }

}
