package org.order.processor.infra.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrap;
    @Value("${apps.type-mapping-producer}")
    private String typeMapping;
    @Value("${apps.topic-producer}")
    private String topicProducer;
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> factory = new HashMap<>();
        factory.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrap);
        factory.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        factory.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
        factory.put(ProducerConfig.ACKS_CONFIG, "all");
        factory.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        factory.put(JacksonJsonSerializer.TYPE_MAPPINGS, typeMapping);
        return new DefaultKafkaProducerFactory<>(factory);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name(topicProducer)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
