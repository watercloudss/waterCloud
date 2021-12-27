package com.watercloud.flash.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class OpenFeignConfig implements RequestInterceptor{
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        requestTemplate.header("token",token);
    }
}
