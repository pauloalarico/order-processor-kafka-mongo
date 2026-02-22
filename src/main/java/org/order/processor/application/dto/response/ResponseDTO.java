package org.order.processor.application.dto.response;

import org.order.processor.domain.enums.Status;

import java.math.BigDecimal;

public record ResponseDTO(
        Status status,
        String correlationId,
        BigDecimal totalValue
) {
}
