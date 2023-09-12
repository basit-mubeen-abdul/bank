package com.fabrick.bank.account.balance.mapper;

import com.fabrick.bank.account.balance.inbound.AccountBalanceDTO;
import com.fabrick.bank.account.balance.outbound.AccountBalanceOutboundDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AccountBalanceDTOMapper implements Function<AccountBalanceOutboundDTO, AccountBalanceDTO> {

    @Override
    public AccountBalanceDTO apply(AccountBalanceOutboundDTO accountBalanceOutboundDTO) {
        return AccountBalanceDTO.builder()
                .date(accountBalanceOutboundDTO.date())
                .balance(accountBalanceOutboundDTO.balance())
                .availableBalance(accountBalanceOutboundDTO.availableBalance())
                .currency(accountBalanceOutboundDTO.currency())
                .build();
    }
}
