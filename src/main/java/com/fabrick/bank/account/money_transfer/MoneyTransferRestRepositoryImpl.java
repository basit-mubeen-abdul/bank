package com.fabrick.bank.account.money_transfer;

import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferInputDTO;
import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferOutputDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MoneyTransferRestRepositoryImpl implements MoneyTransferRestRepository {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    private final String createMoneyTransferUrl;

    private final String authSchema;

    private final String authKey;

    public MoneyTransferRestRepositoryImpl(RestTemplate restTemplate,
                                           @Value("${fabrick.baseurl}") String baseUrl,
                                           @Value("${fabrick.create.money.transfer.url}") String createMoneyTransferUrl,
                                           @Value("${fabrick.headers.auth.schema}") String authSchema,
                                           @Value("${fabrick.headers.auth.key}") String authKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.createMoneyTransferUrl = createMoneyTransferUrl;
        this.authSchema = authSchema;
        this.authKey = authKey;
    }

    @Override
    public CreateMoneyTransferOutputDTO create(Long accountId, CreateMoneyTransferInputDTO createMoneyTransferInputDTO) {
        String url = baseUrl + createMoneyTransferUrl.replace("{accountId}", String.valueOf(accountId));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", authSchema);
        headers.set("Api-Key", authKey);

        HttpEntity<CreateMoneyTransferInputDTO> entity = new HttpEntity<>(createMoneyTransferInputDTO, headers);

        ResponseEntity<CreateMoneyTransferResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                CreateMoneyTransferResponseDTO.class
        );

        return response.getBody().payload();
    }
}
