package org.order.processor.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.processor.application.dto.request.CreateOrderDTO;
import org.order.processor.domain.entitie.OrderProcessor;
import org.order.processor.domain.repository.OrderProcessorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateTotalUseCase {
    private final OrderProcessorRepository repository;


    public void calculateAndSaveTotal(CreateOrderDTO dto) {
        var orderProcessor = new OrderProcessor(dto);
        repository.save(orderProcessor);
    }
}
