package com.fabrick.bank.account.transaction.mapper;

import com.fabrick.bank.account.transaction.dto.inbound.AccountTransactionDTO;
import com.fabrick.bank.account.transaction.dto.outbound.AccountTransactionOutboundDTO;
import com.fabrick.bank.account.transaction.dto.outbound.TransactionTypeOutboundDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AccountTransactionDTOMapper implements Function<AccountTransactionOutboundDTO, AccountTransactionDTO> {

    @Override
    public AccountTransactionDTO apply(AccountTransactionOutboundDTO accountTransactionOutboundDTO) {
        return AccountTransactionDTO.builder()
                .transactionId(accountTransactionOutboundDTO.transactionId())
                .operationId(accountTransactionOutboundDTO.operationId())
                .accountingDate(accountTransactionOutboundDTO.accountingDate())
                .valueDate(accountTransactionOutboundDTO.valueDate())
                .type(accountTransactionOutboundDTO.type() != null ? new TransactionTypeOutboundDTO(accountTransactionOutboundDTO.type().enumeration(),
                        accountTransactionOutboundDTO.type().value()) : null)
                .amount(accountTransactionOutboundDTO.amount())
                .currency(accountTransactionOutboundDTO.currency())
                .description(accountTransactionOutboundDTO.description())
                .build();
    }
}
