package com.fabrick.bank.account.money_transfer.dto;

public record AddressDTO(
        String address,
        String city,
        String countryCode
) {
}
