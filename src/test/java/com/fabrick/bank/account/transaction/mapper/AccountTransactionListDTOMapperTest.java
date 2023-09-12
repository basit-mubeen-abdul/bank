package com.fabrick.bank.account.transaction.mapper;

import com.fabrick.bank.account.transaction.dto.inbound.AccountTransactionDTO;
import com.fabrick.bank.account.transaction.dto.outbound.AccountTransactionOutboundDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountTransactionListDTOMapperTest {

    private final static String EXPECTED_TRANSACTION_ID = "1331714087";
    private final static String EXPECTED_ACCOUNTING_DATE = "2019-04-01";
    private final static double EXPECTED_AMOUNT = -800;
    private final static String EXPECTED_CURRENCY = "EUR";

    private AccountTransactionListDTOMapper sut;

    @Mock
    private AccountTransactionDTOMapper accountTransactionDTOMapper;

    @Captor
    private ArgumentCaptor<AccountTransactionOutboundDTO> argumentCaptor;

    @BeforeEach
    void setUp() {
        sut = new AccountTransactionListDTOMapper(accountTransactionDTOMapper);
    }

    @Test
    void apply() {
        List<AccountTransactionOutboundDTO> outboundTransactionList = buildExceptedOutboundResponse();
        List<AccountTransactionDTO> expected = buildExceptedResponse();
        AccountTransactionDTO[] array = expected.toArray(AccountTransactionDTO[]::new);
        given(accountTransactionDTOMapper.apply(any(AccountTransactionOutboundDTO.class)))
                .willReturn(array[0], Arrays.copyOfRange(array, 1, array.length));

        var result = sut.apply(outboundTransactionList);

        verify(accountTransactionDTOMapper, times(outboundTransactionList.size()))
                .apply(argumentCaptor.capture());
        verifyNoMoreInteractions(accountTransactionDTOMapper);
        assertEquals(argumentCaptor.getAllValues(), outboundTransactionList);
        assertEquals(expected, result);
    }

    private List<AccountTransactionOutboundDTO> buildExceptedOutboundResponse() {
        AccountTransactionOutboundDTO expected = AccountTransactionOutboundDTO.builder()
                .transactionId(EXPECTED_TRANSACTION_ID)
                .accountingDate(EXPECTED_ACCOUNTING_DATE)
                .amount(EXPECTED_AMOUNT)
                .currency(EXPECTED_CURRENCY)
                .build();
        return List.of(expected);
    }

    private List<AccountTransactionDTO> buildExceptedResponse() {
        AccountTransactionDTO expected = AccountTransactionDTO.builder()
                .transactionId(EXPECTED_TRANSACTION_ID)
                .accountingDate(EXPECTED_ACCOUNTING_DATE)
                .amount(EXPECTED_AMOUNT)
                .currency(EXPECTED_CURRENCY)
                .build();
        return List.of(expected);
    }
}