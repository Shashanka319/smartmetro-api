package com.smartmetro.exception;

public class StationNotFoundException extends Exception {
    public StationNotFoundException() {
        super();
    }
    public StationNotFoundException(String message) {
        super(message);
    }
    public StationNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
