package com.fabrick.bank.account.money_transfer;

import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferOutputDTO;
import com.fabrick.bank.common.ErrorDTO;

public record CreateMoneyTransferResponseDTO(
        String status,
        ErrorDTO[] errors,
        CreateMoneyTransferOutputDTO payload
) {

    public static class Builder {
        private String status;
        private ErrorDTO[] errors;
        private CreateMoneyTransferOutputDTO payload;

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

        public Builder payload(CreateMoneyTransferOutputDTO payload) {
            this.payload = payload;
            return this;
        }

        public CreateMoneyTransferResponseDTO build() {
            return new CreateMoneyTransferResponseDTO(status, errors, payload);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
