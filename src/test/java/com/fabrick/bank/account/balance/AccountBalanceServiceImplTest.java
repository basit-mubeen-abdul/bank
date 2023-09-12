package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.outbound.AccountBalanceOutboundDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AccountBalanceServiceImplTest {

    private final static Long INPUT_ACCOUNT_ID = 12345678L;
    private final static Double EXPECTED_BALANCE = 29.64;
    private final static Double EXPECTED_AVAILABLE_BALANCE = 29.64;
    private final static String EXPECTED_BALANCE_DATE = "2018-08-17";

    private AccountBalanceServiceImpl sut;

    @Mock
    private AccountBalanceRestRepository accountBalanceRestRepository;

    @BeforeEach
    void setUp() {
        sut = new AccountBalanceServiceImpl(accountBalanceRestRepository);
    }

    @Test
    void shouldFindAccountBalance() {

        AccountBalanceOutboundDTO expected = AccountBalanceOutboundDTO.builder()
                .date(EXPECTED_BALANCE_DATE)
                .balance(EXPECTED_BALANCE)
                .availableBalance(EXPECTED_AVAILABLE_BALANCE)
                .build();
        given(accountBalanceRestRepository.find(INPUT_ACCOUNT_ID))
                .willReturn(expected);

        var result = sut.find(INPUT_ACCOUNT_ID);

        verify(accountBalanceRestRepository)
                .find(INPUT_ACCOUNT_ID);
        verifyNoMoreInteractions(accountBalanceRestRepository);
        assertEquals(EXPECTED_BALANCE_DATE, result.date());
        assertEquals(EXPECTED_BALANCE, result.balance());
        assertEquals(EXPECTED_AVAILABLE_BALANCE, result.availableBalance());
    }
}