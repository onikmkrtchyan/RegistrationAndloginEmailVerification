package com.onik.spring.security.jwt.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("For this ID does not exists user " + id);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
