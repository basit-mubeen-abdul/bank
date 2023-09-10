package com.fabrick.bank.account.money_transfer.dto;

public record FeeDTO(
        String feeCode,
        String description,
        double amount,
        String currency
) {
}
