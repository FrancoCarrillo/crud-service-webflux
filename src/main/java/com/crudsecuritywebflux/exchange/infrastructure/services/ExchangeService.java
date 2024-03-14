package com.crudsecuritywebflux.exchange.infrastructure.services;

import com.crudsecuritywebflux.exchange.api.model.request.CreateExchangeRequest;
import com.crudsecuritywebflux.exchange.core.entities.Exchange;
import com.crudsecuritywebflux.exchange.core.repositories.CurrencyRepository;
import com.crudsecuritywebflux.exchange.core.repositories.ExchangeRepository;
import com.crudsecuritywebflux.exchange.core.repositories.ExchangeTypeRepository;
import com.crudsecuritywebflux.exchange.infrastructure.interfaces.IExchangeService;
import com.crudsecuritywebflux.shared.exceptions.CustomException;
import com.crudsecuritywebflux.shared.models.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ExchangeService implements IExchangeService {

    private final CurrencyRepository currencyRepository;
//    private final UserRepository userRepository;
    private final ExchangeRepository exchangeRepository;
    private final ExchangeTypeRepository exchangeTypeRepository;


    // TODO: Validate if user exists
    @Override
    public Mono<MessageResponse> createExchange(CreateExchangeRequest request) {
        return currencyRepository.findById(request.getOriginCurrencyId())
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Origin currency not found")))
                .flatMap(originCurrency -> currencyRepository.findById(request.getDestinationCurrencyId())
                        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Destination currency not found")))
                        .flatMap(destinationCurrency -> exchangeTypeRepository.findByOriginCurrencyIdAndDestinationCurrencyId(originCurrency.getId(), destinationCurrency.getId())
                                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Exchange type not found")))
                                .flatMap(exchangeType -> {
                                    Exchange exchange = Exchange.builder()
                                            .originAmount(request.getOriginAmount())
                                            .destinationAmount(request.getOriginAmount() * exchangeType.getExchangeRate())
                                            .userId(request.getUserId())
                                            .exchangeTypeId(exchangeType.getId())
                                            .createdDate(LocalDateTime.now())
                                            .build();

                                    return exchangeRepository.save(exchange)
                                            .thenReturn(MessageResponse.builder()
                                                    .message("Exchange created successfully")
                                                    .build());
                                })));
    }
}
