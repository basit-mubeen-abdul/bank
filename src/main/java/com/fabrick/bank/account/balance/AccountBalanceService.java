package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.inbound.AccountBalanceDTO;

public interface AccountBalanceService {

    AccountBalanceDTO find(Long accountId);
}
