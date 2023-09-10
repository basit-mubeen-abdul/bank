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
}
