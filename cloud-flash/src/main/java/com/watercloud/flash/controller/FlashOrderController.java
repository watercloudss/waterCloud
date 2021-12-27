package com.watercloud.flash.controller;


import com.watercloud.flash.api.FlashItemFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * ç§’æ€è®¢å•è¡¨ 前端控制器
 * </p>
 *
 * @author lly
 * @since 2021-12-25
 */
@RestController
@RequestMapping("/flash-order")
public class FlashOrderController {
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private static final String url = "http://cloud-webmagic";
    @Autowired
    private FlashItemFeign flashItemFeign;

    @GetMapping("/flash")
    public String getHttp(){
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTY0MDM2OTg3NywiaWF0IjoxNjQwMzY4MDc3LCJ1c2VybmFtZSI6ImFkbWluIn0.R_ve__4KP4tYamEBXLBdsL5Ar_C6lHVlBNk5tXhSdpA");
//        org.springframework.http.HttpEntity<String> requestEntity =
//                new  org.springframework.http.HttpEntity(requestHeaders);
//        ResponseEntity<String> response = restTemplate.exchange(url+"/sys-log/kafka", HttpMethod.GET, requestEntity, String.class);
//        String sttr = response.getBody();
        String sttr = flashItemFeign.sendKafka();
        return sttr;
//        return restTemplate.getForObject(url+"/sys-log/kafka",String.class);
    }

}
