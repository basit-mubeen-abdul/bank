package com.fabrick.bank.account.transaction.dto.outbound;

public record AccountTransactionOutboundDTO(
        String transactionId,
        String operationId,
        String accountingDate,
        String valueDate,
        TransactionTypeOutboundDTO type,
        Double amount,
        String currency,
        String description
) {

    public static class Builder {

        private String transactionId;
        private String operationId;
        private String accountingDate;
        private String valueDate;
        private TransactionTypeOutboundDTO type;
        private Double amount;
        private String currency;
        private String description;

        private Builder() {
        }

        public Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder operationId(String operationId) {
            this.operationId = operationId;
            return this;
        }

        public Builder accountingDate(String accountingDate) {
            this.accountingDate = accountingDate;
            return this;
        }

        public Builder valueDate(String valueDate) {
            this.valueDate = valueDate;
            return this;
        }

        public Builder type(TransactionTypeOutboundDTO type) {
            this.type = type;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public AccountTransactionOutboundDTO build() {
            return new AccountTransactionOutboundDTO(transactionId, operationId, accountingDate, valueDate,
                    type, amount, currency, description);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
