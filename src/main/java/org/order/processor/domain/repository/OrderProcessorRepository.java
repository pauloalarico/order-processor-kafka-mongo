package org.order.processor.domain.repository;

import org.order.processor.domain.entitie.OrderProcessor;

public interface OrderProcessorRepository {
    OrderProcessor save(OrderProcessor orderProcessor);
}
