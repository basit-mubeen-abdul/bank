package com.fabrick.bank.account.balance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AccountBalanceRestRepositoryImplTest {

    private final static Long INPUT_ACCOUNT_ID = 12345678L;
    private final static Double EXPECTED_BALANCE = 29.64;
    private final static Double EXPECTED_AVAILABLE_BALANCE = 29.64;
    private final static String EXPECTED_BALANCE_DATE = "2018-08-17";

    private final static String BASE_URL = "https://sandbox.platfr.io";
    private final static String ACCOUNT_BALANCE_URL = "/api/gbs/banking/v4.0/accounts/{accountId}/balance";
    private final static String AUTH_SCHEMA = "S2S";
    private final static String AUTH_KEY = "S2S12345678";

    private AccountBalanceRestRepositoryImpl sut;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        sut = new AccountBalanceRestRepositoryImpl(restTemplate,
                BASE_URL,
                ACCOUNT_BALANCE_URL,
                AUTH_SCHEMA,
                AUTH_KEY);
    }

    @Test
    void shouldFindAccountBalance() {

        AccountBalanceDTO expectedAccountBalance = AccountBalanceDTO.builder()
                .date(EXPECTED_BALANCE_DATE)
                .balance(EXPECTED_BALANCE)
                .availableBalance(EXPECTED_AVAILABLE_BALANCE)
                .build();
        AccountBalanceResponseDTO expectedResponse = AccountBalanceResponseDTO.builder()
                .status("OK")
                .payload(expectedAccountBalance)
                .build();
        String url = BASE_URL + ACCOUNT_BALANCE_URL.replace("{accountId}", String.valueOf(INPUT_ACCOUNT_ID));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", AUTH_SCHEMA);
        headers.set("Api-Key", AUTH_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        given(restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                AccountBalanceResponseDTO.class)).willReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        var result = sut.find(INPUT_ACCOUNT_ID);

        verify(restTemplate)
                .exchange(url, HttpMethod.GET, entity, AccountBalanceResponseDTO.class);
        verifyNoMoreInteractions(restTemplate);
        assertEquals(EXPECTED_BALANCE_DATE, result.date());
        assertEquals(EXPECTED_BALANCE, result.balance());
        assertEquals(EXPECTED_AVAILABLE_BALANCE, result.availableBalance());
    }
}