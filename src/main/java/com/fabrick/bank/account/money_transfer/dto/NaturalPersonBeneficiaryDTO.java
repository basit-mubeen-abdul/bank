package com.fabrick.bank.account.money_transfer.dto;

import jakarta.validation.constraints.NotBlank;

public record NaturalPersonBeneficiaryDTO(

        @NotBlank(message = "The Italian Partita IVA of the legal person is required")
        String fiscalCode1,

        String fiscalCode2,
        String fiscalCode3,
        String fiscalCode4,
        String fiscalCode5
) {
}
