package com.fabrick.bank.account.balance.mapper;

import com.fabrick.bank.account.balance.dto.outbound.AccountBalanceOutboundDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountBalanceDTOMapperTest {

    private final static Double EXPECTED_BALANCE = 29.64;
    private final static Double EXPECTED_AVAILABLE_BALANCE = 29.64;
    private final static String EXPECTED_BALANCE_DATE = "2018-08-17";

    private AccountBalanceDTOMapper sut;

    @BeforeEach
    void setUp() {
        sut = new AccountBalanceDTOMapper();
    }

    @Test
    void apply() {

        AccountBalanceOutboundDTO accountBalanceOutboundDTO = AccountBalanceOutboundDTO.builder()
                .date(EXPECTED_BALANCE_DATE)
                .balance(EXPECTED_BALANCE)
                .availableBalance(EXPECTED_AVAILABLE_BALANCE)
                .build();

        var result = sut.apply(accountBalanceOutboundDTO);

        assertEquals(EXPECTED_BALANCE_DATE, result.date());
        assertEquals(EXPECTED_BALANCE, result.balance());
        assertEquals(EXPECTED_AVAILABLE_BALANCE, result.availableBalance());

    }
}