package com.crudsecuritywebflux.exchange.infrastructure.interfaces;

import com.crudsecuritywebflux.exchange.api.model.request.UpdateCreateExchangeTypeRequest;
import com.crudsecuritywebflux.exchange.core.entities.ExchangeType;
import com.crudsecuritywebflux.shared.models.response.MessageResponse;
import reactor.core.publisher.Mono;

public interface IExchangeTypeService {

    Mono<ExchangeType> getExchangeTypeById(Long id);

    Mono<MessageResponse> createExchangeType(UpdateCreateExchangeTypeRequest request);

    Mono<MessageResponse> updateExchangeType(UpdateCreateExchangeTypeRequest request, Long exchangeTypeId);
}
