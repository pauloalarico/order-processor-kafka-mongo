package org.order.processor.infra.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.order.processor.domain.model.OrderProcessor;
import org.order.processor.domain.repository.OrderProcessorRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderProcessRepositoryImpl implements OrderProcessorRepository {
    private final OrderProcessMongoRepository repository;

    @Override
    public OrderProcessor save(OrderProcessor orderProcessor) {
        return repository.save(orderProcessor);
    }
}
