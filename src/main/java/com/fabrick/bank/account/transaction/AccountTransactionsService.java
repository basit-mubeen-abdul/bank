package com.fabrick.bank.account.transaction;

import com.fabrick.bank.account.transaction.dto.outbound.AccountTransactionDTO;

import java.util.List;

public interface AccountTransactionsService {

    List<AccountTransactionDTO> find(Long accountId, String fromAccountingDate, String toAccountingDate);
}
