package com.watercloud.flash.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.watercloud.flash.feignapi.FlashItemFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @SentinelResource(value = "cloudproviderflash",blockHandler = "handleException")
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

    public String handleException(BlockException exception){
        return "被限流了！";
    }
}
