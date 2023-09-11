package com.fabrick.bank.account.money_transfer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreditorDTO(

        @NotBlank(message = "The name is required.")
        String name,

        @Valid @NotNull(message = "The accountCode is required.")
        AccountDTO account,

        AddressDTO address
) {

        public static class Builder {

                private String name;
                private AccountDTO account;
                private AddressDTO address;

                private Builder() {
                }

                public Builder name(String name) {
                        this.name = name;
                        return this;
                }

                public Builder account(AccountDTO account) {
                        this.account = account;
                        return this;
                }

                public Builder address(AddressDTO address) {
                        this.address = address;
                        return this;
                }

                public CreditorDTO build() {
                        return new CreditorDTO(name, account, address);
                }
        }

        public static Builder builder() {
                return new Builder();
        }
}
