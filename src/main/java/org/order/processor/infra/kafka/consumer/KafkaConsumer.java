package org.order.processor.infra.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.order.processor.application.dto.request.CreateOrderDTO;
import org.order.processor.application.usecase.CalculateTotalUseCase;
import org.order.processor.domain.model.OrderProcessor;
import org.order.processor.infra.kafka.producer.OrderProcessProducer;
import org.order.processor.infra.mapper.OrderProcessMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
   private final CalculateTotalUseCase calculate;
   private final OrderProcessProducer producer;
   private final OrderProcessMapper mapper;

    @KafkaListener(topics = "${apps.kafka-listener}")
    public void consume(ConsumerRecord<String, CreateOrderDTO> consumerRecord, Acknowledgment acknowledgment) {
        try {
            OrderProcessor orderProcessor = calculate.calculateAndSaveTotal(consumerRecord.value());
            var dto = mapper.toResponseDTO(orderProcessor);
            producer.send(dto);
            acknowledgment.acknowledge();
            log.info("Request sent with success, for correlation id {}, orderId {}", consumerRecord.value().correlationId(), consumerRecord.value().orderId());

        } catch (Exception e) {
         log.warn("Error processing the message, correlationId: {}", consumerRecord.value().correlationId());
        }
    }
}
