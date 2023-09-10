package com.fabrick.bank.account.transaction;

import java.math.BigDecimal;

public record AccountTransactionDTO(
        String transactionId,
        String operationId,
        String accountingDate,
        String valueDate,
        TransactionTypeDTO type,
        BigDecimal amount,
        String currency,
        String description
) {
}
