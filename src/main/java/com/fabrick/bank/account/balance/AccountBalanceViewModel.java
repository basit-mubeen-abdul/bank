package com.fabrick.bank.account.balance;

import com.fabrick.bank.common.ErrorDTO;

public record AccountBalanceViewModel(

        String status,
        ErrorDTO[] errors,
        AccountBalanceDTO payload

) {
}
