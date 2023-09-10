package com.fabrick.bank.account.balance;

public interface AccountBalanceRestRepository {

    AccountBalanceDTO find(Long accountId);
}
