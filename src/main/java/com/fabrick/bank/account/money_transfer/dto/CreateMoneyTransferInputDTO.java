package com.fabrick.bank.account.money_transfer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMoneyTransferInputDTO(

        @Valid @NotNull(message = "Creditor information is required")
        CreditorDTO creditor,

        String executionDate,
        String uri,

        @NotBlank(message = "The description is required.")
        String description,

        @DecimalMin(value = "0.0", inclusive = false, message = "The amount is required.")
        double amount,

        @NotBlank(message = "The currency is required.")
        String currency,

        boolean isUrgent,
        boolean isInstant,
        String feeType,
        String feeAccountId,

        @Valid
        TaxReliefDTO taxRelief
) {
}
