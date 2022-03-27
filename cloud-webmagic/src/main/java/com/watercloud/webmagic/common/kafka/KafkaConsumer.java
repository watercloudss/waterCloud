package com.watercloud.webmagic.common.kafka;

import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class KafkaConsumer {
//    @KafkaListener(topics = {"topic-test"})
    public void consume(String message) {
        log.info("## consume message: {}", message);
    }
}
