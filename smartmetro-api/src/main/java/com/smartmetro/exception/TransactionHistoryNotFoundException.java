package com.smartmetro.exception;

public class TransactionHistoryNotFoundException extends Exception {
    public TransactionHistoryNotFoundException() {
        super();
    }
    public TransactionHistoryNotFoundException(String message) {
        super(message);
    }
    public TransactionHistoryNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
