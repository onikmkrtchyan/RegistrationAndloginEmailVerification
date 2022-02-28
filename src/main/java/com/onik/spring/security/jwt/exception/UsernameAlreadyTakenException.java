package com.onik.spring.security.jwt.exception;

public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String username) {
        super("For this username already exists account " + username);
    }
}
