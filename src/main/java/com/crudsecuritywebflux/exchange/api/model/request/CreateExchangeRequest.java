package com.crudsecuritywebflux.exchange.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateExchangeRequest {
    private Double originAmount;
    private Long originCurrencyId;
    private Long destinationCurrencyId;
    private Long userId;
}
