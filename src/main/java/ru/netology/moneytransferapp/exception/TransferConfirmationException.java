package ru.netology.moneytransferapp.exception;

public class TransferConfirmationException extends RuntimeException{
    public TransferConfirmationException(String msg) {
        super(msg);
    }
}
