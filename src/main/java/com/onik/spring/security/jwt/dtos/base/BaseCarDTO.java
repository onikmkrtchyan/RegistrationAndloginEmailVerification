package com.onik.spring.security.jwt.dtos.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BaseCarDTO {
    @NotBlank(message = " blank")
    @Size(min = 7, max = 7, message = "should contain 7 symbols, ex. '33DD333' ")
    private String carNumber;
    @NotBlank(message = " blank")
    @Size(min = 1, max = 20, message = "should contain 1 - 20 symbols")
    private String model;
    @NotBlank(message = " blank")
    @Size(min = 1, max = 20, message = "should contain 1 - 20 symbols")
    private String color;
}
