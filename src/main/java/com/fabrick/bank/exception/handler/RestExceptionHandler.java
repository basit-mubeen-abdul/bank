package com.fabrick.bank.exception.handler;

import com.fabrick.bank.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    protected ResponseEntity<Object> handleRestException(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
