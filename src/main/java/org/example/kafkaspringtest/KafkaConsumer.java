package org.example.kafkaspringtest;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@Getter
public class KafkaConsumer {
    private String message;
    private final CountDownLatch latch = new CountDownLatch(1);

    @Value("${app-prop}")
    private String prop;

    @PostConstruct
    public void init() {
        System.out.println("Property value in kafka consumer: " + prop);
    }

    @KafkaListener(topics = "topic1", groupId = "group1")
    public void consume(String message) {
        this.message = message;
        latch.countDown();
    }
}
