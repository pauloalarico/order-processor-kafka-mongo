package org.order.processor.domain.repository;

import org.order.processor.domain.model.OrderProcessor;

public interface OrderProcessorRepository {
    OrderProcessor save(OrderProcessor orderProcessor);
}
