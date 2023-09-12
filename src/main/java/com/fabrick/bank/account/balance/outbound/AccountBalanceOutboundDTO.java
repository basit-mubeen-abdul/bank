package com.fabrick.bank.account.balance.outbound;

public record AccountBalanceOutboundDTO(
        String date,
        Double balance,
        Double availableBalance,
        String currency
) {

    public static class Builder {

        private String date;
        private Double balance;
        private Double availableBalance;
        private String currency;

        private Builder() {
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        public Builder availableBalance(Double availableBalance) {
            this.availableBalance = availableBalance;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public AccountBalanceOutboundDTO build() {
            return new AccountBalanceOutboundDTO(date, balance, availableBalance, currency);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}