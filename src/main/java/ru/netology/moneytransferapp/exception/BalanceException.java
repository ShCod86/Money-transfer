package ru.netology.moneytransferapp.exception;

public class BalanceException extends RuntimeException {
    public BalanceException(String msg) {
        super(msg);
    }
}
