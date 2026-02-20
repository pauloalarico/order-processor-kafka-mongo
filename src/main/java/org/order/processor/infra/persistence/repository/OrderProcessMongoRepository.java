package org.order.processor.infra.persistence.repository;

import org.order.processor.domain.model.OrderProcessor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderProcessMongoRepository extends MongoRepository<OrderProcessor, String> {
}
