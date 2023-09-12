package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.dto.inbound.AccountBalanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountBalanceControllerImpl implements AccountBalanceController {

    private final AccountBalanceService findAccountBalanceService;

    @Autowired
    public AccountBalanceControllerImpl(AccountBalanceService findAccountBalanceService) {
        this.findAccountBalanceService = findAccountBalanceService;
    }

    @Override
    public ResponseEntity<AccountBalanceDTO> find(Long accountId) {
        return ResponseEntity.ok(findAccountBalanceService.find(accountId));
    }
}
