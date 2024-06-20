package org.example.kafkaspringtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, topics = "topic1")
public class KafkaConsumerTest {
    private static final String TOPIC = "topic1";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaConsumer kafkaConsumer;


    @Test
    void testConsume() throws InterruptedException {
        String message = "Hello World";

        kafkaTemplate.send(TOPIC, message);

//        Thread.sleep(20);

        assertThat(kafkaConsumer.getLatch().await(5, TimeUnit.SECONDS)).isTrue();

        assertThat(kafkaConsumer.getMessage()).isEqualTo(message);
    }
}
