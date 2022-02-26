package com.onik.spring.security.jwt.exception;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super("Password Mismatch");
    }
}
