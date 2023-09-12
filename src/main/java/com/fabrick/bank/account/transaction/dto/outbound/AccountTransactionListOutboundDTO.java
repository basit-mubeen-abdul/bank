package com.fabrick.bank.account.transaction.dto.outbound;

import java.util.List;

public record AccountTransactionListOutboundDTO(

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

        public AccountTransactionListOutboundDTO build() {
            return new AccountTransactionListOutboundDTO(list);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
