package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.outbound.AccountBalanceOutboundDTO;

public interface AccountBalanceRestRepository {

    AccountBalanceOutboundDTO find(Long accountId);
}
