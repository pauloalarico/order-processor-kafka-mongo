package org.order.processor.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.processor.application.dto.request.CreateOrderDTO;
import org.order.processor.domain.model.OrderProcessor;
import org.order.processor.domain.repository.OrderProcessorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateTotalUseCase {
    private final OrderProcessorRepository repository;


    public OrderProcessor calculateAndSaveTotal(CreateOrderDTO dto) {
        var orderProcessor = new OrderProcessor(dto);
        double random = Math.random();
        if (random > 0.82) {
            orderProcessor.cancelValue();
        }
        return repository.save(orderProcessor);
    }
}
