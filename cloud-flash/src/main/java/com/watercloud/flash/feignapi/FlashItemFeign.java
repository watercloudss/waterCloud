package com.watercloud.flash.feignapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cloud-webmagic")
@Component
public interface FlashItemFeign {
    @GetMapping("/sys-log/kafka")
    String sendKafka();
}
