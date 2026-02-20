package org.order.processor.application.dto.response;

import org.order.processor.domain.model.OrderProcessor;
import org.order.processor.domain.enums.Status;

import java.math.BigDecimal;

public record ResponseDTO(
        Status status,
        String correlationId,
        BigDecimal totalValue
) {
    public ResponseDTO(OrderProcessor processor) {
        this(processor.getStatus(), processor.getCorrelationId(), processor.getTotalValue());
    }
}
