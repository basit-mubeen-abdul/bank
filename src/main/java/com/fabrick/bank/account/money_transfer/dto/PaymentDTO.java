package com.fabrick.bank.account.money_transfer.dto;

public record PaymentDTO(
        CreditorDTO creditor,
        String executionDate,
        String uri,
        String description,
        double amount,
        String currency,
        boolean isUrgent,
        boolean isInstant,
        String feeType,
        String feeAccountId,
        TaxReliefDTO taxRelief
) {
}
