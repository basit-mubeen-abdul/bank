package com.fabrick.bank.account.money_transfer;

import com.fabrick.bank.account.money_transfer.dto.AccountDTO;
import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferInputDTO;
import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferOutputDTO;
import com.fabrick.bank.account.money_transfer.dto.CreditorDTO;
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
class MoneyTransferRestRepositoryImplTest {

    private final static Long INPUT_ACCOUNT_ID = 12345678L;
    private final static String EXPECTED_MONEY_TRANSFER_ID = "452516859427";
    private final static String EXPECTED_STATUS = "EXECUTED";

    private final static String BASE_URL = "https://sandbox.platfr.io";
    private final static String ACCOUNT_MONEY_TRANSFER_URL = "/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
    private final static String AUTH_SCHEMA = "S2S";
    private final static String AUTH_KEY = "S2S12345678";

    private MoneyTransferRestRepositoryImpl sut;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        sut = new MoneyTransferRestRepositoryImpl(restTemplate,
                BASE_URL,
                ACCOUNT_MONEY_TRANSFER_URL,
                AUTH_SCHEMA,
                AUTH_KEY);
    }

    @Test
    void create() {

        AccountDTO accountDTO = AccountDTO.builder()
                .accountCode("IT23A0336844430152923804660")
                .build();
        CreditorDTO creditorDTO = CreditorDTO.builder()
                .name("John Doe")
                .account(accountDTO)
                .build();
        CreateMoneyTransferInputDTO inputDTO = CreateMoneyTransferInputDTO.builder()
                .creditor(creditorDTO)
                .description("Payment invoice 75/2017")
                .amount(800)
                .currency("EUR")
                .build();
        CreateMoneyTransferOutputDTO outputDTO = CreateMoneyTransferOutputDTO.builder()
                .moneyTransferId(EXPECTED_MONEY_TRANSFER_ID)
                .status(EXPECTED_STATUS)
                .build();
        CreateMoneyTransferResponseDTO expectedResponse = CreateMoneyTransferResponseDTO.builder()
                .status("OK")
                .payload(outputDTO)
                .build();
        String url = BASE_URL + ACCOUNT_MONEY_TRANSFER_URL.replace("{accountId}", String.valueOf(INPUT_ACCOUNT_ID));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", AUTH_SCHEMA);
        headers.set("Api-Key", AUTH_KEY);
        HttpEntity<CreateMoneyTransferInputDTO> entity = new HttpEntity<>(inputDTO, headers);
        given(restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                CreateMoneyTransferResponseDTO.class)).willReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        var result = sut.create(INPUT_ACCOUNT_ID, inputDTO);

        verify(restTemplate)
                .exchange(url, HttpMethod.POST, entity, CreateMoneyTransferResponseDTO.class);
        verifyNoMoreInteractions(restTemplate);
        assertEquals(EXPECTED_MONEY_TRANSFER_ID, result.moneyTransferId());
        assertEquals(EXPECTED_STATUS, result.status());
    }
}