package com.watercloud.flash.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.watercloud.flash.common.FlashBlockHandler;
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
    @SentinelResource(value = "cloudproviderflash",fallback = "handleFallback2",blockHandler = "flashHandlerException",blockHandlerClass = FlashBlockHandler.class)
    public String getHttp(){
        if(true) {
            int a= 1/0;
        }
        String sttr = flashItemFeign.sendKafka();
        return sttr;
    }

    public String handleFallback2(){
        return "服务降级返回";
    }
}
