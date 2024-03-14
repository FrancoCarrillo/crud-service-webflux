package com.crudsecuritywebflux.exchange.api.controller;

import com.crudsecuritywebflux.exchange.api.model.request.UpdateCreateExchangeTypeRequest;
import com.crudsecuritywebflux.exchange.core.entities.ExchangeType;
import com.crudsecuritywebflux.exchange.infrastructure.interfaces.IExchangeTypeService;
import com.crudsecuritywebflux.shared.models.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/exchange-type")
@Slf4j
@RequiredArgsConstructor
public class ExchangeTypeController {

    private final IExchangeTypeService exchangeTypeService;
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ExchangeType>> getById(@PathVariable Long id) {
        return exchangeTypeService.getExchangeTypeById(id)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<MessageResponse>> register(@RequestBody UpdateCreateExchangeTypeRequest request) {
        return exchangeTypeService.createExchangeType(request)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<MessageResponse>> update(@RequestBody UpdateCreateExchangeTypeRequest request, @PathVariable Long id) {
        return exchangeTypeService.updateExchangeType(request, id)
                .map(ResponseEntity::ok);
    }

}
