package com.crudsecuritywebflux.exchange.core.repositories;

import com.crudsecuritywebflux.exchange.core.entities.Currency;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends ReactiveCrudRepository<Currency, Long> {
}
