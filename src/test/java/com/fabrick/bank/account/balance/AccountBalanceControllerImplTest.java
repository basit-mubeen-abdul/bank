package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.inbound.AccountBalanceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AccountBalanceControllerImplTest {

    private final static Long INPUT_ACCOUNT_ID = 12345678L;
    private final static Double EXPECTED_BALANCE = 29.64;
    private final static Double EXPECTED_AVAILABLE_BALANCE = 29.64;
    private final static String EXPECTED_BALANCE_DATE = "2018-08-17";

    private AccountBalanceControllerImpl sut;

    @Mock
    private AccountBalanceService accountBalanceService;

    @BeforeEach
    void setUp() {
        sut = new AccountBalanceControllerImpl(accountBalanceService);
    }

    @Test
    void shouldFindAccountBalance() {

        AccountBalanceDTO expected = AccountBalanceDTO.builder()
                .date(EXPECTED_BALANCE_DATE)
                .balance(EXPECTED_BALANCE)
                .availableBalance(EXPECTED_AVAILABLE_BALANCE)
                .build();
        given(accountBalanceService.find(INPUT_ACCOUNT_ID))
                .willReturn(expected);


        var result = sut.find(INPUT_ACCOUNT_ID);

        verify(accountBalanceService)
                .find(INPUT_ACCOUNT_ID);
        verifyNoMoreInteractions(accountBalanceService);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(EXPECTED_BALANCE_DATE, result.getBody().date());
        assertEquals(EXPECTED_BALANCE, result.getBody().balance());
        assertEquals(EXPECTED_AVAILABLE_BALANCE, result.getBody().availableBalance());
    }
}