package ru.netology.moneytransferapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.netology.moneytransferapp.model.*;
import ru.netology.moneytransferapp.repository.CardRepository;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransferServiceTest {
    private CardRepository cardRepository;
    private TransferService transferService;
    private Transfer transfer;
    private Card from;
    private Card to;

    @BeforeEach
    void setUp() {
        cardRepository = mock(CardRepository.class);
        transferService = new TransferService(cardRepository);
        transfer = new Transfer("1111111111111111", "12/24", "111", "2222222222222222", new Amount(10000, "RUB"));
        from = new Card("1111111111111111", "12/24", "111", new Amount(20000, "Rub"));
        to = new Card("2222222222222222", "12/24", "111", new Amount(10, "Rub"));
    }

    @Test
    void doTransferTest() {
        when(cardRepository.getCard("1111111111111111")).thenReturn(from);
        when(cardRepository.getCard("2222222222222222")).thenReturn(to);

        ResponseEntity<TransferResult> responseEntity = transferService.doTransfer(transfer);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void confirmOperationTest() {
        when(cardRepository.getCard("1111111111111111")).thenReturn(from);
        when(cardRepository.getCard("2222222222222222")).thenReturn(to);
        String id = Objects.requireNonNull(transferService.doTransfer(transfer).getBody()).getOperationId();
        ConfirmOperation confirmOperation = new ConfirmOperation(id, "0000");

        ResponseEntity<TransferResult> responseEntity = transferService.confirmOperation(confirmOperation);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}