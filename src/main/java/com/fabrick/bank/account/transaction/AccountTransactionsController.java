package com.fabrick.bank.account.transaction;

import com.fabrick.bank.account.transaction.dto.outbound.AccountTransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Account")
@RequestMapping("/api/v1/account")
public interface AccountTransactionsController {

    @GetMapping("{accountId}/transactions")
    @Operation(
            operationId = "account transactions",
            description = "Find account transactions"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account transactions loaded successfully"
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
    ResponseEntity<List<AccountTransactionDTO>> find(@PathVariable("accountId") Long accountId,
                                                     @RequestParam(required = true) String fromAccountingDate,
                                                     @RequestParam(required = true) String toAccountingDate);
}
