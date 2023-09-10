package com.fabrick.bank.account.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountTransactionsControllerImpl implements AccountTransactionsController {

    private final AccountTransactionsService accountTransactionsService;

    public AccountTransactionsControllerImpl(AccountTransactionsService accountTransactionsService) {
        this.accountTransactionsService = accountTransactionsService;
    }

    @Override
    public ResponseEntity<List<AccountTransactionDTO>> find(Long accountId, String fromAccountingDate, String toAccountingDate) {
        return ResponseEntity.ok(accountTransactionsService.find(accountId, fromAccountingDate, toAccountingDate));
    }
}
