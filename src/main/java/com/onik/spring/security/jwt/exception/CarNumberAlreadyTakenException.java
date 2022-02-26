package com.onik.spring.security.jwt.exception;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CarNumberAlreadyTakenException extends RuntimeException {
    public CarNumberAlreadyTakenException(String carNumber) {
    }
}
