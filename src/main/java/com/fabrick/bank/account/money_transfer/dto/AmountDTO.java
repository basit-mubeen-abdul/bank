package com.fabrick.bank.account.money_transfer.dto;

public record AmountDTO(
        double debtorAmount,
        String debtorCurrency,
        double creditorAmount,
        String creditorCurrency,
        String creditorCurrencyDate,
        double exchangeRate
) {
}
