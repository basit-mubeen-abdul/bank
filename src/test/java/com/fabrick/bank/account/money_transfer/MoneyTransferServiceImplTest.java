package com.fabrick.bank.account.money_transfer;

import com.fabrick.bank.account.money_transfer.dto.AccountDTO;
import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferInputDTO;
import com.fabrick.bank.account.money_transfer.dto.CreateMoneyTransferOutputDTO;
import com.fabrick.bank.account.money_transfer.dto.CreditorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class MoneyTransferServiceImplTest {

    private final static Long INPUT_ACCOUNT_ID = 12345678L;
    private final static String EXPECTED_MONEY_TRANSFER_ID = "452516859427";
    private final static String EXPECTED_STATUS = "EXECUTED";

    private MoneyTransferServiceImpl sut;

    @Mock
    private MoneyTransferRestRepository moneyTransferRestRepository;

    @BeforeEach
    void setUp() {
        sut = new MoneyTransferServiceImpl(moneyTransferRestRepository);
    }

    @Test
    void shouldCreateMoneyTransfer() {

        AccountDTO accountDTO = AccountDTO.builder()
                .accountCode("IT23A0336844430152923804660")
                .build();
        CreditorDTO creditorDTO = CreditorDTO.builder()
                .name("John Doe")
                .account(accountDTO)
                .build();
        CreateMoneyTransferInputDTO inputDTO = CreateMoneyTransferInputDTO.builder()
                .creditor(creditorDTO)
                .description("Payment invoice 75/2017")
                .amount(800)
                .currency("EUR")
                .build();
        CreateMoneyTransferOutputDTO outputDTO = CreateMoneyTransferOutputDTO.builder()
                .moneyTransferId(EXPECTED_MONEY_TRANSFER_ID)
                .status(EXPECTED_STATUS)
                .build();
        given(moneyTransferRestRepository.create(INPUT_ACCOUNT_ID, inputDTO))
                .willReturn(outputDTO);

        var result = sut.create(INPUT_ACCOUNT_ID, inputDTO);

        verify(moneyTransferRestRepository)
                .create(INPUT_ACCOUNT_ID, inputDTO);
        verifyNoMoreInteractions(moneyTransferRestRepository);
        assertEquals(EXPECTED_MONEY_TRANSFER_ID, result.moneyTransferId());
        assertEquals(EXPECTED_STATUS, result.status());
    }
}