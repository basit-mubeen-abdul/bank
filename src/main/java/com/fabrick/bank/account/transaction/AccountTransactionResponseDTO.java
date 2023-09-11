package com.fabrick.bank.account.transaction;

import com.fabrick.bank.common.ErrorDTO;

import java.util.List;

public record AccountTransactionResponseDTO(
        String status,
        ErrorDTO[] errors,
        List<AccountTransactionDTO> payload
) {

    public static class Builder {
        private String status;
        private ErrorDTO[] errors;
        private List<AccountTransactionDTO> payload;

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

        public Builder payload(List<AccountTransactionDTO> payload) {
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
