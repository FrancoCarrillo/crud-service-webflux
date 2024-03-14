package com.crudsecuritywebflux.exchange.core.entities;

import com.crudsecuritywebflux.security.core.entities.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exchange {
    @Id
    private Long id;

    private Double originAmount;
    private Double destinationAmount;

    private Long userId;

    private Long exchangeTypeId;

    private LocalDateTime createdDate;
}
