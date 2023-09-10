package com.fabrick.bank.account.money_transfer.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountDTO(

        @NotBlank(message = "The accountCode is required.")
        String accountCode,

        String bicCode
) {
}
