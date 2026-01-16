package com.example.finance.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDTO(
    Long id,
    BigDecimal sum,
    LocalDate date,
    String categoryName) {
}
