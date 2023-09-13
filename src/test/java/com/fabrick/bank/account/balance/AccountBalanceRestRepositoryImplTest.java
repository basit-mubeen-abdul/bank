package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.dto.inbound.AccountBalanceDTO;
import com.fabrick.bank.account.balance.dto.outbound.AccountBalanceOutboundDTO;
import com.fabrick.bank.account.balance.dto.outbound.AccountBalanceResponseDTO;
import com.fabrick.bank.account.balance.mapper.AccountBalanceDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

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

    @Mock
    private AccountBalanceDTOMapper accountBalanceDTOMapper;

    @BeforeEach
    void setUp() {
        sut = new AccountBalanceRestRepositoryImpl(restTemplate,
                accountBalanceDTOMapper,
                BASE_URL,
                ACCOUNT_BALANCE_URL,
                AUTH_SCHEMA,
                AUTH_KEY);
    }

    @Test
    void shouldFindAccountBalance() {

        AccountBalanceOutboundDTO accountBalance = buildExpectedAccountBalance();
        AccountBalanceResponseDTO accountBalanceResponseDTO = buildExpectedResponse(accountBalance);
        AccountBalanceDTO expectedAccountBalanceDTO = buildExpectedAccountBalanceDTO();
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
                AccountBalanceResponseDTO.class)).willReturn(new ResponseEntity<>(accountBalanceResponseDTO, HttpStatus.OK));
        given(accountBalanceDTOMapper.apply(accountBalance))
                .willReturn(expectedAccountBalanceDTO);

        var result = sut.find(INPUT_ACCOUNT_ID);

        var inOrder = Mockito.inOrder(restTemplate, accountBalanceDTOMapper);
        inOrder.verify(restTemplate)
                .exchange(url, HttpMethod.GET, entity, AccountBalanceResponseDTO.class);
        inOrder.verify(accountBalanceDTOMapper)
                .apply(accountBalance);
        inOrder.verifyNoMoreInteractions();
        assertEquals(EXPECTED_BALANCE_DATE, result.date());
        assertEquals(EXPECTED_BALANCE, result.balance());
        assertEquals(EXPECTED_AVAILABLE_BALANCE, result.availableBalance());
    }

    private AccountBalanceOutboundDTO buildExpectedAccountBalance() {
        return AccountBalanceOutboundDTO.builder()
                .date(EXPECTED_BALANCE_DATE)
                .balance(EXPECTED_BALANCE)
                .availableBalance(EXPECTED_AVAILABLE_BALANCE)
                .build();
    }

    private AccountBalanceResponseDTO buildExpectedResponse(AccountBalanceOutboundDTO expectedAccountBalance) {
        return AccountBalanceResponseDTO.builder()
                .status("OK")
                .payload(expectedAccountBalance)
                .build();
    }

    private AccountBalanceDTO buildExpectedAccountBalanceDTO() {
        return AccountBalanceDTO.builder()
                .date(EXPECTED_BALANCE_DATE)
                .balance(EXPECTED_BALANCE)
                .availableBalance(EXPECTED_AVAILABLE_BALANCE)
                .build();
    }
}