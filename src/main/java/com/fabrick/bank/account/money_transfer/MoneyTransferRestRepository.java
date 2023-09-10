package com.fabrick.bank.account.money_transfer;

import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferInputDTO;
import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferOutputDTO;

public interface MoneyTransferRestRepository {

    CreateMoneyTransferOutputDTO create(Long accountId, CreateMoneyTransferInputDTO createMoneyTransferInputDTO);
}
