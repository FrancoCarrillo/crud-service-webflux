package com.crudsecuritywebflux.exchange.infrastructure.interfaces;

import com.crudsecuritywebflux.exchange.api.model.request.CreateExchangeRequest;
import com.crudsecuritywebflux.shared.models.response.MessageResponse;
import reactor.core.publisher.Mono;

public interface IExchangeService {

    Mono<MessageResponse> createExchange(CreateExchangeRequest request);
}
