package com.crudsecuritywebflux.exchange.core.repositories;

import com.crudsecuritywebflux.exchange.core.entities.ExchangeType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeTypeRepository extends ReactiveCrudRepository<ExchangeType, Long> {
}
