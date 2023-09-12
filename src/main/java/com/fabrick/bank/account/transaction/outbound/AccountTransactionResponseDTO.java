package com.fabrick.bank.account.transaction.outbound;

import com.fabrick.bank.common.ErrorDTO;

public record AccountTransactionResponseDTO(
        String status,
        ErrorDTO[] errors,
        AccountTransactionList payload
) {

    public static class Builder {
        private String status;
        private ErrorDTO[] errors;
        private AccountTransactionList payload;

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

        public Builder payload(AccountTransactionList payload) {
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
