package com.smartmetro.exception;

public class RouteStationNotFoundException extends Exception {
    public RouteStationNotFoundException() {
        super();
    }
    public RouteStationNotFoundException(String message) {
        super(message);
    }
    public RouteStationNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
