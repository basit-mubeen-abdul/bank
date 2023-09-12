package com.fabrick.bank.account.balance.dto.outbound;

import com.fabrick.bank.common.ErrorDTO;

public record AccountBalanceResponseDTO(
        String status,
        ErrorDTO[] errors,
        AccountBalanceOutboundDTO payload
) {

    public static class Builder {
        private String status;
        private ErrorDTO[] errors;
        private AccountBalanceOutboundDTO payload;

        private Builder() {
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder errors(ErrorDTO[] errors) {
            this.errors = errors;
            return this;
        }

        public Builder payload(AccountBalanceOutboundDTO payload) {
            this.payload = payload;
            return this;
        }

        public AccountBalanceResponseDTO build() {
            return new AccountBalanceResponseDTO(status, errors, payload);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
