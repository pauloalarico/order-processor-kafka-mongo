package org.order.processor.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.processor.application.dto.request.CreateOrderDTO;
import org.order.processor.domain.model.OrderProcessor;
import org.order.processor.domain.repository.OrderProcessorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CalculateTotalUseCase {
    private final OrderProcessorRepository repository;


    public OrderProcessor calculateAndSaveTotal(CreateOrderDTO dto) {
        String correlationId = dto.correlationId();
        String product = dto.product();
        Integer quantity = dto.quantity();
        BigDecimal price = dto.price();
        var orderProcessor = new OrderProcessor(correlationId, product, price, quantity);
        double random = Math.random();
        if (random > 0.82) {
            orderProcessor.cancelValue();
        }
        return repository.save(orderProcessor);
    }
}
