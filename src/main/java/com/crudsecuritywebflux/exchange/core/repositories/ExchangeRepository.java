package com.crudsecuritywebflux.exchange.core.repositories;

import com.crudsecuritywebflux.exchange.core.entities.Exchange;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends ReactiveCrudRepository<Exchange, Long> {
}
