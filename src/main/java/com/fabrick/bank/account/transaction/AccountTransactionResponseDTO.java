package com.fabrick.bank.account.transaction;

import com.fabrick.bank.common.ErrorDTO;

import java.util.List;

public record AccountTransactionResponseDTO(
        String status,
        ErrorDTO[] errors,
        List<AccountTransactionDTO> payload
) {
}
