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
        //TODO complete the builder
        public static class Builder {

                private CreditorDTO creditor;
                private String description;
                private double amount;
                private String currency;

                private Builder() {
                }

                public Builder creditor(CreditorDTO creditor) {
                        this.creditor = creditor;
                        return this;
                }

                public Builder description(String description) {
                        this.description = description;
                        return this;
                }

                public Builder amount(double amount) {
                        this.amount = amount;
                        return this;
                }

                public Builder currency(String currency) {
                        this.currency = currency;
                        return this;
                }

                public CreateMoneyTransferInputDTO build() {
                        return new CreateMoneyTransferInputDTO(creditor, "executionDate", "uri",
                                description, amount, currency, false, false, "feeType",
                                "feeAccountId", null);
                }
        }

        public static Builder builder() {
                return new Builder();
        }
}
