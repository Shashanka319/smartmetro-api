package com.smartmetro.exception;

public class PaymentNotFoundException extends Exception {
    public PaymentNotFoundException() {
        super();
    }
    public PaymentNotFoundException(String message) {
        super(message);
    }
    public PaymentNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
