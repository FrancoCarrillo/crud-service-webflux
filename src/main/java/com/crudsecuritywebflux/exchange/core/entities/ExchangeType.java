package com.crudsecuritywebflux.exchange.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExchangeType {
    @Id
    private Long id;

    private Double exchangeRate;

    private Long originCurrencyId;

    private Long destinationCurrencyId;


}
