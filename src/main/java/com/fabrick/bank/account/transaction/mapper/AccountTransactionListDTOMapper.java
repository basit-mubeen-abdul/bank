package com.fabrick.bank.account.transaction.mapper;

import com.fabrick.bank.account.transaction.dto.inbound.AccountTransactionDTO;
import com.fabrick.bank.account.transaction.dto.outbound.AccountTransactionOutboundDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class AccountTransactionListDTOMapper implements Function<List<AccountTransactionOutboundDTO>, List<AccountTransactionDTO>> {

    private final AccountTransactionDTOMapper accountTransactionDTOMapper;

    public AccountTransactionListDTOMapper(AccountTransactionDTOMapper accountTransactionDTOMapper) {
        this.accountTransactionDTOMapper = accountTransactionDTOMapper;
    }

    @Override
    public List<AccountTransactionDTO> apply(List<AccountTransactionOutboundDTO> accountTransactionOutboundDTOS) {
        return accountTransactionOutboundDTOS.stream()
                .map(accountTransactionDTOMapper)
                .toList();
    }
}
