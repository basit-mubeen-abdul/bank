package com.fabrick.bank.account.money_transfer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CreditorDTO(

        @NotBlank(message = "The name is required.")
        String name,

        @Valid
        AccountDTO account,

        AddressDTO address
) {
}
