package com.fabrick.bank.common;

public record ErrorDTO(
        String code,
        String description,
        String params
) {
}
