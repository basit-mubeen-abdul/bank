package com.fabrick.bank.account.transaction.outbound;

import com.fabrick.bank.account.transaction.AccountTransactionDTO;

import java.util.List;

public record AccountTransactionList(

        List<AccountTransactionDTO> list

) {

    public static class Builder {

        private List<AccountTransactionDTO> list;

        private Builder() {
        }

        public Builder list(List<AccountTransactionDTO> list) {
            this.list = list;
            return this;
        }

        public AccountTransactionList build() {
            return new AccountTransactionList(list);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
