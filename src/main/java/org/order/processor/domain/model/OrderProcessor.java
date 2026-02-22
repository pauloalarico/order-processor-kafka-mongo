package org.order.processor.domain.model;

import lombok.Getter;
import org.order.processor.application.dto.request.CreateOrderDTO;
import org.order.processor.domain.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "order-processor")
@Getter
public class OrderProcessor {
    @Id
    private String correlationId;
    private final String product;
    private final BigDecimal totalValue;
    private Status status;

    public OrderProcessor(String correlationId, String product, BigDecimal totalValue, Integer quantity) {
        this.correlationId = correlationId;
        this.product = product;
        this.totalValue = calculateTotalValue(totalValue, quantity);
        this.status = Status.ACTIVE;
    }

    private BigDecimal calculateTotalValue(BigDecimal amount, Integer quantity) {
        return amount.multiply(BigDecimal.valueOf(quantity));
    }

    public void cancelValue() {
        this.status = Status.CANCELED;
    }
}
