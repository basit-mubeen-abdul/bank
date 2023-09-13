package com.fabrick.bank.exception.handler;

import com.fabrick.bank.account.balance.AccountBalanceRestRepositoryImpl;
import com.fabrick.bank.account.balance.dto.outbound.AccountBalanceResponseDTO;
import com.fabrick.bank.account.balance.mapper.AccountBalanceDTOMapper;
import com.fabrick.bank.exception.RestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RestException.class, AccountBalanceRestRepositoryImpl.class, RestTemplate.class, AccountBalanceDTOMapper.class })
@RestClientTest
class RestResponseErrorHandlerTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private RestTemplateBuilder builder;

    @Test
    public void  givenRemoteApiCall_when404Error_thenThrowNotFound() {
        Assertions.assertNotNull(this.builder);
        Assertions.assertNotNull(this.server);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "authSchema");
        headers.set("Api-Key", "authKey");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = this.builder
                .errorHandler(new RestResponseErrorHandler())
                .build();

        this.server
                .expect(ExpectedCount.once(), requestTo("/api/gbs/banking/v4.0/accounts/123456/balance"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        Assertions.assertThrows(RestException.class, () -> {
            ResponseEntity<AccountBalanceResponseDTO> response = restTemplate.exchange(
                    "/api/gbs/banking/v4.0/accounts/123456/balance",
                    HttpMethod.GET,
                    entity,
                    AccountBalanceResponseDTO.class
            );
        });
    }

}