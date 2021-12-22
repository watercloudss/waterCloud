package com.watercloud.webmagic.common.kafka;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class KafkaMessage {
    private String id;
    private String message;
    private LocalDateTime time;
}
