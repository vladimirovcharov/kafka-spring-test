package org.example.kafkaspringtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class KafkaSpringTestApplicationTests {

    @Value("${app-prop}")
    private String prop;

    @Test
    void contextLoads() {
        System.out.println("Property value in context test: " + prop);
    }

}
