package ru.netology.moneytransferapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.moneytransferapp.exception.BalanceException;
import ru.netology.moneytransferapp.exception.InputException;
import ru.netology.moneytransferapp.exception.TransferConfirmationException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(InputException.class)
    public ResponseEntity<String> ieHandler(InputException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BalanceException.class)
    public ResponseEntity<String> balanceHandler(BalanceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferConfirmationException.class)
    public ResponseEntity<String> tcHandler(TransferConfirmationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
