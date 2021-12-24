package com.watercloud.webmagic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
* 单体启动类
*/
@SpringBootApplication
@EnableDiscoveryClient
public class CloudWebmagicApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudWebmagicApplication.class,args);
    }
}