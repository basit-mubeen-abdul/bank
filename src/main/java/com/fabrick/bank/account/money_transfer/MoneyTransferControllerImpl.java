package com.fabrick.bank.account.money_transfer;

import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferInputDTO;
import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MoneyTransferControllerImpl implements MoneyTransferController {

    private final MoneyTransferService moneyTransferService;

    public MoneyTransferControllerImpl(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @Override
    public ResponseEntity<CreateMoneyTransferOutputDTO> create(Long accountId, CreateMoneyTransferInputDTO createMoneyTransferInputDTO) {
        return ResponseEntity.ok(moneyTransferService.create(accountId, createMoneyTransferInputDTO));
    }
}
