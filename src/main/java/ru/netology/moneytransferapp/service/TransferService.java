package ru.netology.moneytransferapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netology.moneytransferapp.exception.BalanceException;
import ru.netology.moneytransferapp.exception.InputException;
import ru.netology.moneytransferapp.exception.TransferConfirmationException;
import ru.netology.moneytransferapp.model.*;
import ru.netology.moneytransferapp.repository.CardRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class TransferService {

    private final CardRepository cardRepository;
    private final Map<String, ConfirmOperation> confirmOperations = new ConcurrentHashMap<>();
    private final Map<String, Transfer> transferRepo = new ConcurrentHashMap<>();
    private final AtomicInteger latestId = new AtomicInteger(0);
    private final Double COMISSION_FEE = 0.01;

    public TransferService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public ResponseEntity<TransferResult> doTransfer(Transfer transfer) {
        Card fromCard = cardRepository.getCard(transfer.getCardFromNumber());
        Card toCard = cardRepository.getCard(transfer.getCardToNumber());

        if (fromCard == null) {
            log.error("Card from not found.");
            throw new InputException("Card from not found.");
        }
        if (toCard == null) {
            log.error("Card to not found.");
            throw new InputException("Card to not found.");
        }
        if (fromCard == toCard) {
            log.error("Two identical card numbers.");
            throw new InputException("Two identical card numbers.");
        }
        if (!fromCard.getCardValidTill().equals(transfer.getCardFromValidTill()) ||
                !fromCard.getCardCVV().equals(transfer.getCardFromCVV())) {
            log.error("Incorrect data input.");
            throw new InputException("Incorrect data input.");
        }
        if (fromCard.getBalance().getValue() < transfer.getAmount().getValue()) {
            log.error("Not enough money.");
            throw new BalanceException("Not enough money.");
        }
        String operationId = String.valueOf(latestId.getAndIncrement());
        String code = "0000";
        confirmOperations.put(operationId, new ConfirmOperation(operationId, code));
        transferRepo.put(operationId, transfer);
        TransferResult result = new TransferResult();
        result.setOperationId(operationId);
        confirmOperations.forEach((key, value) -> System.out.println(key + ":" + value));
        transferRepo.forEach((key, value) -> System.out.println(key + ":" + value));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<TransferResult> confirmOperation(ConfirmOperation confirm) {
        String operationId = confirm.getOperationId();
        String code = confirmOperations.get(operationId).getCode();
        Transfer transfer = transferRepo.get(operationId);
        if (transfer == null) {
            log.error("Operation not found.");
            throw new TransferConfirmationException("Operation not found.");
        }
        if (!confirm.getCode().equals(code) || code.isEmpty()) {
            log.error("Invalid code.");
            throw new TransferConfirmationException("Invalid code.");
        }

        log.info(balanceChange(transfer.getCardFromNumber(), transfer.getCardToNumber(), transfer.getAmount()));

        TransferResult result = new TransferResult();
        result.setOperationId(operationId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public String balanceChange(String fromCardNumber, String toCardNumber, Amount amount) {
        Card fromCard = cardRepository.getCard(fromCardNumber);
        Card toCard = cardRepository.getCard(toCardNumber);
        int fromCardBalance = fromCard.getBalance().getValue();
        int toCardBalance = toCard.getBalance().getValue();
        int commission = (int) (amount.getValue() * COMISSION_FEE);
        fromCard.getBalance().setValue(fromCardBalance - (amount.getValue() + commission));
        toCard.getBalance().setValue(toCardBalance + amount.getValue());
        return String.format("""
                Successful transfer:
                from card %s to card %s
                transfer size %d
                commission size %d""", fromCard.getCardNumber(), toCard.getCardNumber(), amount.getValue(), commission);
    }
}