package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.inbound.AccountBalanceDTO;
import com.fabrick.bank.account.balance.mapper.AccountBalanceDTOMapper;
import com.fabrick.bank.account.balance.outbound.AccountBalanceResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountBalanceRestRepositoryImpl implements AccountBalanceRestRepository {

    private final RestTemplate restTemplate;

    private final AccountBalanceDTOMapper accountBalanceDTOMapper;

    private final String baseUrl;

    private final String accountBalanceUrl;

    private final String authSchema;

    private final String authKey;

    public AccountBalanceRestRepositoryImpl(RestTemplate restTemplate,
                                            AccountBalanceDTOMapper accountBalanceDTOMapper,
                                            @Value("${fabrick.baseurl}") String baseUrl,
                                            @Value("${fabrick.account.balance.url}") String accountBalanceUrl,
                                            @Value("${fabrick.headers.auth.schema}") String authSchema,
                                            @Value("${fabrick.headers.auth.key}") String authKey) {
        this.restTemplate = restTemplate;
        this.accountBalanceDTOMapper = accountBalanceDTOMapper;
        this.baseUrl = baseUrl;
        this.accountBalanceUrl = accountBalanceUrl;
        this.authSchema = authSchema;
        this.authKey = authKey;
    }

    @Override
    public AccountBalanceDTO find(Long accountId) {

        String url = baseUrl + accountBalanceUrl.replace("{accountId}", String.valueOf(accountId));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", authSchema);
        headers.set("Api-Key", authKey);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<AccountBalanceResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                AccountBalanceResponseDTO.class
        );

        return accountBalanceDTOMapper.apply(response.getBody().payload());
    }
}
