package com.fabrick.bank.account.money_transfer.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountDTO(

        @NotBlank(message = "The accountCode is required.")
        String accountCode,

        String bicCode
) {
        public static class Builder {

                private String accountCode;
                private String bicCode;

                private Builder() {
                }

                public Builder accountCode(String accountCode) {
                        this.accountCode = accountCode;
                        return this;
                }

                public Builder bicCode(String bicCode) {
                        this.bicCode = bicCode;
                        return this;
                }

                public AccountDTO build() {
                        return new AccountDTO(accountCode, bicCode);
                }
        }

        public static Builder builder() {
                return new Builder();
        }

}
