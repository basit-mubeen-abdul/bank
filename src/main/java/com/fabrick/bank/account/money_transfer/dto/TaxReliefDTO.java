package com.fabrick.bank.account.money_transfer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaxReliefDTO(

        String taxReliefId,

        @NotNull(message = "Flag to indicate if the tax relief is related to upgrades of common condominium spaces is required")
        boolean isCondoUpgrade,

        @NotBlank(message = "The fiscal code of the money transfer creditor is required")
        String creditorFiscalCode,

        @NotBlank(message = "The type of the tax relief beneficiary is required")
        String beneficiaryType,

        @Valid
        NaturalPersonBeneficiaryDTO naturalPersonBeneficiary,

        LegalPersonBeneficiaryDTO legalPersonBeneficiary
) {
}
