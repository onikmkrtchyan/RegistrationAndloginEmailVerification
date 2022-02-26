package com.onik.spring.security.jwt.exception;

public class CarNumberAlreadyTakenException extends RuntimeException {
    public CarNumberAlreadyTakenException(String carNumber) {
        super("Car number already exists: " + carNumber);
    }
}
