package com.fabrick.bank.account.balance;

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
                .date("2018-08-17")
                .balance(29.64)
                .availableBalance(29.64)
                .build();
        given(accountBalanceService.find(INPUT_ACCOUNT_ID))
                .willReturn(expected);


        var result = sut.find(INPUT_ACCOUNT_ID);

        verify(accountBalanceService)
                .find(INPUT_ACCOUNT_ID);
        verifyNoMoreInteractions(accountBalanceService);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}