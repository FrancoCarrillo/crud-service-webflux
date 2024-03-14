package com.crudsecuritywebflux.exchange.infrastructure.services;

import com.crudsecuritywebflux.exchange.api.model.request.UpdateCreateExchangeTypeRequest;
import com.crudsecuritywebflux.exchange.core.entities.ExchangeType;
import com.crudsecuritywebflux.exchange.core.repositories.CurrencyRepository;
import com.crudsecuritywebflux.exchange.core.repositories.ExchangeTypeRepository;
import com.crudsecuritywebflux.exchange.infrastructure.interfaces.IExchangeTypeService;
import com.crudsecuritywebflux.shared.exceptions.CustomException;
import com.crudsecuritywebflux.shared.models.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeTypeService implements IExchangeTypeService {
    private final ExchangeTypeRepository exchangeTypeRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public Mono<ExchangeType> getExchangeTypeById(Long id) {
        return exchangeTypeRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Exchange type not found")));
    }

    @Override
    public Mono<MessageResponse> createExchangeType(UpdateCreateExchangeTypeRequest request) {

        return currencyRepository.findById(request.getOriginCurrencyId())
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Origin currency not found")))
                .flatMap(originCurrency -> currencyRepository.findById(request.getDestinationCurrencyId())
                        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,"Destination currency not found")))
                        .flatMap(destinationCurrency -> {
                            ExchangeType exchangeType = ExchangeType.builder()
                                    .exchangeRate(request.getExchangeRate())
                                    .originCurrencyId(originCurrency.getId())
                                    .destinationCurrencyId(destinationCurrency.getId())
                                    .build();

                            return exchangeTypeRepository.save(exchangeType)
                                    .map(exchangeType1 -> new MessageResponse("Exchange type created successfully"));
                        }));
    }

    @Override
    public Mono<MessageResponse> updateExchangeType(UpdateCreateExchangeTypeRequest request, Long exchangeTypeId) {
        return exchangeTypeRepository.findById(exchangeTypeId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,"Exchange type not found")))
                .flatMap(exchangeType -> currencyRepository.findById(request.getOriginCurrencyId())
                        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,"Origin currency not found")))
                        .flatMap(originCurrency -> currencyRepository.findById(request.getDestinationCurrencyId())
                                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,"Destination currency not found")))
                                .flatMap(destinationCurrency -> {
                                    exchangeType.setExchangeRate(request.getExchangeRate());
                                    exchangeType.setOriginCurrencyId(originCurrency.getId());
                                    exchangeType.setDestinationCurrencyId(destinationCurrency.getId());

                                    return exchangeTypeRepository.save(exchangeType)
                                            .map(exchangeType1 -> new MessageResponse("Exchange type updated successfully"));
                                })));
    }
}
