package com.fabrick.bank.account.money_transfer;

import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferInputDTO;
import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferOutputDTO;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    private final MoneyTransferRestRepository moneyTransferRestRepository;

    public MoneyTransferServiceImpl(MoneyTransferRestRepository moneyTransferRestRepository) {
        this.moneyTransferRestRepository = moneyTransferRestRepository;
    }

    @Override
    public CreateMoneyTransferOutputDTO create(Long accountId, CreateMoneyTransferInputDTO createMoneyTransferInputDTO) {
        return moneyTransferRestRepository.create(accountId, createMoneyTransferInputDTO);
    }
}
