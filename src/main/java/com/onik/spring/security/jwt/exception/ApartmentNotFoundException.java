package com.onik.spring.security.jwt.exception;

public class ApartmentNotFoundException extends RuntimeException {
    public ApartmentNotFoundException(Long id) {
        super("For this ID does not exists car " + id);
    }
}
