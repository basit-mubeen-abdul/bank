package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.outbound.AccountBalanceOutboundDTO;

public interface AccountBalanceService {

    AccountBalanceOutboundDTO find(Long accountId);
}
