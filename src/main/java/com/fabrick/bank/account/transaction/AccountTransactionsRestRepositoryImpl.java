package com.fabrick.bank.account.transaction;

import com.fabrick.bank.account.transaction.outbound.AccountTransactionResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class AccountTransactionsRestRepositoryImpl implements AccountTransactionsRestRepository {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    private final String accountTransactionsUrl;

    private final String authSchema;

    private final String authKey;

    public AccountTransactionsRestRepositoryImpl(RestTemplate restTemplate,
                                                @Value("${fabrick.baseurl}") String baseUrl,
                                                @Value("${fabrick.account.transactions.url}") String accountTransactionsUrl,
                                                @Value("${fabrick.headers.auth.schema}") String authSchema,
                                                @Value("${fabrick.headers.auth.key}") String authKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.accountTransactionsUrl = accountTransactionsUrl;
        this.authSchema = authSchema;
        this.authKey = authKey;
    }

    @Override
    public List<AccountTransactionDTO> find(Long accountId, String fromAccountingDate, String toAccountingDate) {

        String url = baseUrl + accountTransactionsUrl.replace("{accountId}", String.valueOf(accountId));

        UriComponents urlWithQueryParams = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("fromAccountingDate",fromAccountingDate)
                .queryParam("toAccountingDate",toAccountingDate)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", authSchema);
        headers.set("Api-Key", authKey);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<AccountTransactionResponseDTO> response = restTemplate.exchange(
                urlWithQueryParams.toString(),
                HttpMethod.GET,
                entity,
                AccountTransactionResponseDTO.class
        );

        return response.getBody().payload().list();
    }
}