package org.order.processor.application.dto.request;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record CreateOrderDTO(
        String orderId,
        String correlationId,
        String status,
        String product,
        Integer quantity,
        BigDecimal price,
        ZonedDateTime createdAt
) {
}
