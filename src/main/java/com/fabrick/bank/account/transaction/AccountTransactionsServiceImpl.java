package com.fabrick.bank.account.transaction;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTransactionsServiceImpl implements AccountTransactionsService {

    private final AccountTransactionsRestRepository accountTransactionsRestRepository;

    public AccountTransactionsServiceImpl(AccountTransactionsRestRepository accountTransactionsRestRepository) {
        this.accountTransactionsRestRepository = accountTransactionsRestRepository;
    }

    @Override
    public List<AccountTransactionDTO> find(Long accountId, String fromAccountingDate, String toAccountingDate) {
        return accountTransactionsRestRepository.find(accountId, fromAccountingDate, toAccountingDate);
    }
}
