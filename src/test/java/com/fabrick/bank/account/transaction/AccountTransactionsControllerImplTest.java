package com.fabrick.bank.account.transaction;

import com.fabrick.bank.account.transaction.dto.inbound.AccountTransactionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AccountTransactionsControllerImplTest {

    private final static Long INPUT_ACCOUNT_ID = 12345678L;
    private final static String FROM_ACCOUNTING_DATE = "2019-04-01";
    private final static String TO_ACCOUNTING_DATE = "2019-04-01";

    private final static String EXPECTED_TRANSACTION_ID = "1331714087";
    private final static String EXPECTED_ACCOUNTING_DATE = "2019-04-01";
    private final static double EXPECTED_AMOUNT = -800;
    private final static String EXPECTED_CURRENCY = "EUR";


    private AccountTransactionsControllerImpl sut;

    @Mock
    private AccountTransactionsService accountTransactionsService;

    @BeforeEach
    void setUp() {
        sut = new AccountTransactionsControllerImpl(accountTransactionsService);
    }

    @Test
    void shouldFindAccountTransactions() {

        AccountTransactionDTO expected = AccountTransactionDTO.builder()
                .transactionId(EXPECTED_TRANSACTION_ID)
                .accountingDate(EXPECTED_ACCOUNTING_DATE)
                .amount(EXPECTED_AMOUNT)
                .currency(EXPECTED_CURRENCY)
                .build();
        List<AccountTransactionDTO> expectedResponse = List.of(expected);
        given(accountTransactionsService.find(INPUT_ACCOUNT_ID, FROM_ACCOUNTING_DATE, TO_ACCOUNTING_DATE))
                .willReturn(expectedResponse);

        var result = sut.find(INPUT_ACCOUNT_ID, FROM_ACCOUNTING_DATE, TO_ACCOUNTING_DATE);

        verify(accountTransactionsService)
                .find(INPUT_ACCOUNT_ID, FROM_ACCOUNTING_DATE, TO_ACCOUNTING_DATE);
        verifyNoMoreInteractions(accountTransactionsService);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(EXPECTED_TRANSACTION_ID, result.getBody().get(0).transactionId());
        assertEquals(EXPECTED_ACCOUNTING_DATE, result.getBody().get(0).accountingDate());
        assertEquals(EXPECTED_AMOUNT, result.getBody().get(0).amount());
        assertEquals(EXPECTED_CURRENCY, result.getBody().get(0).currency());
    }
}