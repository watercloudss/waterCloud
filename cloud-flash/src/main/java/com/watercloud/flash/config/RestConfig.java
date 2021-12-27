package com.watercloud.flash.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced     //配置负载均衡,ribbon的作用，默认是轮询
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
