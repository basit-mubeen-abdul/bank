package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.inbound.AccountBalanceDTO;

public interface AccountBalanceRestRepository {

    AccountBalanceDTO find(Long accountId);
}
