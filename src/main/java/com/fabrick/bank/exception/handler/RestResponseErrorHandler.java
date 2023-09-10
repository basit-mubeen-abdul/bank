package com.fabrick.bank.exception.handler;

import com.fabrick.bank.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestResponseErrorHandler implements ResponseErrorHandler {

    Logger log = LoggerFactory.getLogger(RestResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error("HTTP error: " + response.getStatusCode() + " " + response.getStatusText());
        throw new RestException("Something went wrong, please try again in a few minutes");
    }

}
