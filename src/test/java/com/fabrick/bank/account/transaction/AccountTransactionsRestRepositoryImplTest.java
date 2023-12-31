package com.fabrick.bank.account.transaction;

import com.fabrick.bank.account.transaction.dto.inbound.AccountTransactionDTO;
import com.fabrick.bank.account.transaction.dto.outbound.AccountTransactionListOutboundDTO;
import com.fabrick.bank.account.transaction.dto.outbound.AccountTransactionOutboundDTO;
import com.fabrick.bank.account.transaction.dto.outbound.AccountTransactionResponseDTO;
import com.fabrick.bank.account.transaction.mapper.AccountTransactionListDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountTransactionsRestRepositoryImplTest {

    private final static Long INPUT_ACCOUNT_ID = 12345678L;
    private final static String FROM_ACCOUNTING_DATE = "2019-04-01";
    private final static String TO_ACCOUNTING_DATE = "2019-04-01";

    private final static String EXPECTED_TRANSACTION_ID = "1331714087";
    private final static String EXPECTED_ACCOUNTING_DATE = "2019-04-01";
    private final static double EXPECTED_AMOUNT = -800;
    private final static String EXPECTED_CURRENCY = "EUR";

    private final static String BASE_URL = "https://sandbox.platfr.io";
    private final static String ACCOUNT_TRANSACTIONS_URL = "/api/gbs/banking/v4.0/accounts/{accountId}/transactions";
    private final static String AUTH_SCHEMA = "S2S";
    private final static String AUTH_KEY = "S2S12345678";

    private AccountTransactionsRestRepositoryImpl sut;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AccountTransactionListDTOMapper accountTransactionListDTOMapper;

    @BeforeEach
    void setUp() {
        sut = new AccountTransactionsRestRepositoryImpl(restTemplate,
                accountTransactionListDTOMapper,
                BASE_URL,
                ACCOUNT_TRANSACTIONS_URL,
                AUTH_SCHEMA,
                AUTH_KEY);
    }

    @Test
    void shouldFindAccountTransactions() {

        AccountTransactionResponseDTO expectedOutboundResponse = buildExceptedOutboundResponse();
        List<AccountTransactionDTO> expectedResponse = buildExceptedResponse();
        String url = BASE_URL + ACCOUNT_TRANSACTIONS_URL.replace("{accountId}", String.valueOf(INPUT_ACCOUNT_ID)) +
                "?fromAccountingDate=" + FROM_ACCOUNTING_DATE + "&toAccountingDate=" + TO_ACCOUNTING_DATE;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", AUTH_SCHEMA);
        headers.set("Api-Key", AUTH_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        given(restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                AccountTransactionResponseDTO.class)).willReturn(new ResponseEntity<>(expectedOutboundResponse, HttpStatus.OK));
        given(accountTransactionListDTOMapper.apply(expectedOutboundResponse.payload().list()))
                .willReturn(expectedResponse);

        var result = sut.find(INPUT_ACCOUNT_ID, FROM_ACCOUNTING_DATE, TO_ACCOUNTING_DATE);

        var inOrder = Mockito.inOrder(restTemplate, accountTransactionListDTOMapper);
        inOrder.verify(restTemplate)
                .exchange(url, HttpMethod.GET, entity, AccountTransactionResponseDTO.class);
        inOrder.verify(accountTransactionListDTOMapper)
                .apply(expectedOutboundResponse.payload().list());
        inOrder.verifyNoMoreInteractions();
        assertEquals(EXPECTED_TRANSACTION_ID, result.get(0).transactionId());
        assertEquals(EXPECTED_ACCOUNTING_DATE, result.get(0).accountingDate());
        assertEquals(EXPECTED_AMOUNT, result.get(0).amount());
        assertEquals(EXPECTED_CURRENCY, result.get(0).currency());
    }

    private AccountTransactionResponseDTO buildExceptedOutboundResponse() {
        AccountTransactionOutboundDTO expected = AccountTransactionOutboundDTO.builder()
                .transactionId(EXPECTED_TRANSACTION_ID)
                .accountingDate(EXPECTED_ACCOUNTING_DATE)
                .amount(EXPECTED_AMOUNT)
                .currency(EXPECTED_CURRENCY)
                .build();
        List<AccountTransactionOutboundDTO> expectedAccountTransactionsList = List.of(expected);
        AccountTransactionListOutboundDTO accountTransactionList = AccountTransactionListOutboundDTO.builder()
                .list(expectedAccountTransactionsList)
                .build();
        AccountTransactionResponseDTO expectedResponse = AccountTransactionResponseDTO.builder()
                .status("OK")
                .payload(accountTransactionList)
                .build();
        return expectedResponse;
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