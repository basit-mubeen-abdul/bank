package com.fabrick.bank.account.transaction.dto.outbound;

import com.fabrick.bank.common.ErrorDTO;

public record AccountTransactionResponseDTO(
        String status,
        ErrorDTO[] errors,
        AccountTransactionListOutboundDTO payload
) {

    public static class Builder {
        private String status;
        private ErrorDTO[] errors;
        private AccountTransactionListOutboundDTO payload;

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

        public Builder payload(AccountTransactionListOutboundDTO payload) {
            this.payload = payload;
            return this;
        }

        public AccountTransactionResponseDTO build() {
            return new AccountTransactionResponseDTO(status, errors, payload);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
