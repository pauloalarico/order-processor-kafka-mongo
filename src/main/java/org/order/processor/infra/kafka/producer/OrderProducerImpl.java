package org.order.processor.infra.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.order.processor.application.dto.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducerImpl implements OrderProcessProducer{
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${apps.topic-process}")
    private String topicProducer;

    @Override
    public void send(ResponseDTO command) {
        try {
            kafkaTemplate.send(topicProducer, command.correlationId(), command).get();
        } catch (InterruptedException e) {
            log.error("Unable to send the processor, error: {}, correlationId: {}", e.getMessage(), command.correlationId());
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            Thread.currentThread().interrupt();
            log.error("Thread stopped because of the error: {}, correlationId: {}", e.getMessage(), command.correlationId());
            throw new RuntimeException(e);
        }
    }
}
