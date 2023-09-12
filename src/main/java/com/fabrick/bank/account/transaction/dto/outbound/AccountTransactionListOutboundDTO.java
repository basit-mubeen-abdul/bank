package com.fabrick.bank.account.transaction.dto.outbound;

import java.util.List;

public record AccountTransactionListOutboundDTO(

        List<AccountTransactionOutboundDTO> list

) {

    public static class Builder {

        private List<AccountTransactionOutboundDTO> list;

        private Builder() {
        }

        public Builder list(List<AccountTransactionOutboundDTO> list) {
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
