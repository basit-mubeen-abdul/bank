package com.fabrick.bank.account.transaction;

import com.fabrick.bank.account.transaction.dto.inbound.AccountTransactionDTO;

import java.util.List;

public interface AccountTransactionsRestRepository {

    List<AccountTransactionDTO> find(Long accountId, String fromAccountingDate, String toAccountingDate);

}
