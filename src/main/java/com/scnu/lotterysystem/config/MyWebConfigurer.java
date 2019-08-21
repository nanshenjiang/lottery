package com.scnu.lotterysystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//继承接口WebMvcConfigurer实现管理SpringMvc
@Configuration
public class MyWebConfigurer extends WebMvcConfigurerAdapter {
    //重定向路径
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
    }
}
