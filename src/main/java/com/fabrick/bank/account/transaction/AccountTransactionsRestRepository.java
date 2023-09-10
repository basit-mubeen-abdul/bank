package com.fabrick.bank.account.transaction;

import java.util.List;

public interface AccountTransactionsRestRepository {

    List<AccountTransactionDTO> find(Long accountId, String fromAccountingDate, String toAccountingDate);

}
