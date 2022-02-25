package com.onik.spring.security.jwt.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id){
        super("For this ID does not exists car " + id);
    }
}
