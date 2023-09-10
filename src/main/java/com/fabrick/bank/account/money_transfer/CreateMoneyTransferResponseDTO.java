package com.fabrick.bank.account.money_transfer;

import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferOutputDTO;
import com.fabrick.bank.common.ErrorDTO;

public record CreateMoneyTransferResponseDTO(
        String status,
        ErrorDTO[] errors,
        CreateMoneyTransferOutputDTO payload
) {
}
