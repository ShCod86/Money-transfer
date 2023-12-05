package ru.netology.moneytransferapp.exception;

public class InputException extends RuntimeException {
    public InputException(String msg) {
        super(msg);
    }
}