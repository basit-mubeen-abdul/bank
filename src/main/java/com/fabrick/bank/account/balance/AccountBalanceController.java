package com.fabrick.bank.account.balance;

import com.fabrick.bank.account.balance.dto.inbound.AccountBalanceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Account")
@RequestMapping("/api/v1/account")
public interface AccountBalanceController {

    @GetMapping("{accountId}/balance")
    @Operation(
            operationId = "account balance",
            description = "Find account balance"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account balance loaded successfully"
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
    ResponseEntity<AccountBalanceDTO> find(@PathVariable("accountId") Long accountId);
}
