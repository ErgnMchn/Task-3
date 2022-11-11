package com.carrentalsystem.api.exception;

public class CarNotFoundException extends RuntimeException {
    private String message;

    public CarNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
