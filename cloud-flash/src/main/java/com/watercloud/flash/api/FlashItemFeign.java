package com.watercloud.flash.api;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cloud-webmagic")
@Component
public interface FlashItemFeign {
    @GetMapping("/sys-log/kafka")
    String sendKafka();
}
