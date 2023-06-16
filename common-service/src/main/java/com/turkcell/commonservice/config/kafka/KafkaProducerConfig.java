package com.turkcell.commonservice.config.kafka;

import com.turkcell.commonservice.util.kafka.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {
    @Bean
    KafkaProducer getKafkaProducer(KafkaTemplate<String, Object> template) {
        return new KafkaProducer(template);
    }
}
