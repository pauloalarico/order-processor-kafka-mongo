package org.order.processor.domain.entitie;

import lombok.Getter;
import org.order.processor.application.dto.request.CreateOrderDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "order-processor")
@Getter
public class OrderProcessor {
    @Id
    private String correlationId;
    private String product;
    private BigDecimal totalValue;

    public OrderProcessor(CreateOrderDTO dto) {
        this.correlationId = dto.correlationId();
        this.product = dto.product();
        this.totalValue = calculateTotalValue(dto.price(), dto.quantity());
    }

    private BigDecimal calculateTotalValue(BigDecimal amount, Integer quantity) {
        return amount.multiply(BigDecimal.valueOf(quantity));
    }
}
