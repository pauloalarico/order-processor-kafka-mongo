package org.order.processor.infra.mapper;

import org.order.processor.application.dto.response.ResponseDTO;
import org.order.processor.domain.model.OrderProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessMapper {
    public  ResponseDTO toResponseDTO(OrderProcessor processor) {
        return new ResponseDTO(
                processor.getStatus(),
                processor.getCorrelationId(),
                processor.getTotalValue()
        );
    }
}
