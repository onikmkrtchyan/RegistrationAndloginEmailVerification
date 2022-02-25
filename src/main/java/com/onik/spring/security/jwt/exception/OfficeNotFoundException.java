package com.onik.spring.security.jwt.exception;

public class OfficeNotFoundException extends RuntimeException {
    public OfficeNotFoundException(Long id) {
        super("For this ID does not exists office " + id);
    }
}
