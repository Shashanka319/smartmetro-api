package com.smartmetro.exception;

public class TripNotFoundException extends Exception {
    public TripNotFoundException(){
        super();
    }
    public TripNotFoundException(String message) {
        super(message);
    }
    public TripNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
