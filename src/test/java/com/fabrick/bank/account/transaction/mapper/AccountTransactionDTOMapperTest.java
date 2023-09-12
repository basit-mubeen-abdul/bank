package com.fabrick.bank.account.transaction.mapper;

import com.fabrick.bank.account.transaction.dto.outbound.AccountTransactionOutboundDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTransactionDTOMapperTest {

    private final static String EXPECTED_TRANSACTION_ID = "1331714087";
    private final static String EXPECTED_ACCOUNTING_DATE = "2019-04-01";
    private final static double EXPECTED_AMOUNT = -800;
    private final static String EXPECTED_CURRENCY = "EUR";

    private AccountTransactionDTOMapper sut;

    @BeforeEach
    void setUp() {
        sut = new AccountTransactionDTOMapper();
    }

    @Test
    void apply() {

        AccountTransactionOutboundDTO accountTransactionOutboundDTO = AccountTransactionOutboundDTO.builder()
                .transactionId(EXPECTED_TRANSACTION_ID)
                .accountingDate(EXPECTED_ACCOUNTING_DATE)
                .amount(EXPECTED_AMOUNT)
                .currency(EXPECTED_CURRENCY)
                .build();

        var result = sut.apply(accountTransactionOutboundDTO);

        assertEquals(EXPECTED_TRANSACTION_ID, result.transactionId());
        assertEquals(EXPECTED_ACCOUNTING_DATE, result.accountingDate());
        assertEquals(EXPECTED_AMOUNT, result.amount());
        assertEquals(EXPECTED_CURRENCY, result.currency());
    }
}