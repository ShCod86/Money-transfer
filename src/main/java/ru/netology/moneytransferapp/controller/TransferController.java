package ru.netology.moneytransferapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytransferapp.model.ConfirmOperation;
import ru.netology.moneytransferapp.model.Transfer;
import ru.netology.moneytransferapp.model.TransferResult;
import ru.netology.moneytransferapp.service.TransferService;

@RestController
@CrossOrigin
public class TransferController {
    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResult> transfer(@RequestBody Transfer transfer) {
        return service.doTransfer(transfer);
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<TransferResult> confirmOperation(@RequestBody ConfirmOperation confirm) {
        return service.confirmOperation(confirm);
    }
}
