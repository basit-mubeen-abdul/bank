package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.outbound.AccountBalanceOutboundDTO;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceServiceImpl implements AccountBalanceService {

    private final AccountBalanceRestRepository accountBalanceRestRepository;

    public AccountBalanceServiceImpl(AccountBalanceRestRepository accountBalanceRestRepository) {
        this.accountBalanceRestRepository = accountBalanceRestRepository;
    }

    @Override
    public AccountBalanceOutboundDTO find(Long accountId) {
        return accountBalanceRestRepository.find(accountId);
    }
}
