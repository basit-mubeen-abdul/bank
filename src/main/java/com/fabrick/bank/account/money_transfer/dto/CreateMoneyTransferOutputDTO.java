package com.fabrick.bank.account.money_transfer.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record CreateMoneyTransferOutputDTO(
        String moneyTransferId,
        String status,
        String direction,
        CreditorDTO creditor,
        DebtorDTO debtor,
        String cro,
        String uri,
        String trn,
        String description,
        OffsetDateTime createdDatetime,
        OffsetDateTime accountedDatetime,
        String debtorValueDate,
        String creditorValueDate,
        AmountDTO amount,
        boolean isUrgent,
        boolean isInstant,
        String feeType,
        String feeAccountId,
        List<FeeDTO> fees,
        boolean hasTaxRelief
) {

    //TODO complete the builder
    public static class Builder {

        private String moneyTransferId;
        private String status;

        private Builder() {
        }

        public Builder moneyTransferId(String moneyTransferId) {
            this.moneyTransferId = moneyTransferId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public CreateMoneyTransferOutputDTO build() {
            return new CreateMoneyTransferOutputDTO(moneyTransferId, status, "direction", null, null,
                    "cro", "uri", "trn", "description", null, null, "debtorValueDate",
                    "creditorValueDate", null, false, false, "feeType", "feeType", null, false);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
