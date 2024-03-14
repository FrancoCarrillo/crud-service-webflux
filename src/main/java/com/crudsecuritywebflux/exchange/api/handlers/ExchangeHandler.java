package com.crudsecuritywebflux.exchange.api.handlers;

import com.crudsecuritywebflux.exchange.api.model.request.CreateExchangeRequest;
import com.crudsecuritywebflux.exchange.infrastructure.interfaces.IExchangeService;
import com.crudsecuritywebflux.shared.models.response.MessageResponse;
import com.crudsecuritywebflux.shared.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeHandler {

    private final IExchangeService exchangeService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<CreateExchangeRequest> exchange = request.bodyToMono(CreateExchangeRequest.class)
                .doOnNext(objectValidator::validate);

        return exchange.flatMap(ex -> ServerResponse.ok()
                .body(exchangeService.createExchange(ex), MessageResponse.class));

    }

}
