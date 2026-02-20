package org.order.processor.infra.kafka.producer;

import org.order.processor.application.dto.response.ResponseDTO;

public interface OrderProcessProducer {
    void send(ResponseDTO command);
}
