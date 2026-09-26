package com.smartmetro.exception;

public class MetroCardNotFoundException extends Exception {
    public MetroCardNotFoundException() {
        super();
    }
    public MetroCardNotFoundException(String message) {
        super(message);
    }
    public MetroCardNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
