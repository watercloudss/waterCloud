package com.watercloud.webmagic.config;

import com.watercloud.webmagic.common.jwt.JwtAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtAuthenticationInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/sys-user/login");
    }
    @Bean
    public JwtAuthenticationInterceptor jwtAuthenticationInterceptor() {
        return new JwtAuthenticationInterceptor();
    }
}
