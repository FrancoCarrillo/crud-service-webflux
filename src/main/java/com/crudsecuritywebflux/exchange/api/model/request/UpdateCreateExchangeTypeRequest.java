package com.crudsecuritywebflux.exchange.api.model.request;

import lombok.*;

import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateCreateExchangeTypeRequest {

    @Min(value = 0, message = "Exchange rate must be greater than 0")
    private Double exchangeRate;
    private Long originCurrencyId;
    private Long destinationCurrencyId;
}
