package com.watercloud.webmagic.common.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;

//@Component
@Slf4j
public class KafkaProducer {
    /**
     * 消息 TOPIC
     */
    private static final String TOPIC = "topic-test";

    @Autowired
//    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String id, String message, LocalDateTime time) {
//        KafkaMessage kafkaMessage = KafkaMessage.builder()
//                .id(id)
//                .message(message)
//                .time(time)
//                .build();
//        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, JSONObject.toJSONString(kafkaMessage));
//        // 监听回调
//        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                log.info("## Send message fail ...");
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
//                log.info("## Send message success ...");
//            }
//        });
    }
}
