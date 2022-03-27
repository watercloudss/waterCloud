package com.watercloud.webmagic.kafka;

import com.watercloud.webmagic.common.kafka.KafkaProducer;
//import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {
    @Autowired
//    private KafkaProducer kafkaProducer;

    @Test
    public void sendMessage() throws InterruptedException {
        // 发送 1000 个消息
        for (int i = 0; i < 10; i++) {
            String id = String.valueOf(i+1);
            String message = "我是一条消息"+i;
//            kafkaProducer.sendMessage(id, message, LocalDateTime.now());
        }
        TimeUnit.MINUTES.sleep(1);
    }
}
