package com.fabrick.bank.account.money_transfer;

import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferInputDTO;
import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Account")
@RequestMapping("/api/v1/account")
public interface MoneyTransferController {

    @PostMapping("{accountId}/payments/money-transfers")
    @Operation(
            operationId = "money transfer",
            description = "Creates a new money transfer"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Money transfered successfully"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = {
                    @Content(
                            mediaType = "APPLICATION_JSON",
                            schema = @Schema(example = " { \"message\" : \"accountId not present in input\" }\n")
                    )
            }
    )
    ResponseEntity<CreateMoneyTransferOutputDTO> create(@PathVariable("accountId") Long accountId,
                                                        @Valid @RequestBody CreateMoneyTransferInputDTO createMoneyTransferInputDTO);
}
