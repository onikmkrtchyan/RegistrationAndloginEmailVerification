package com.onik.spring.security.jwt.dtos.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BaseCarDTO {
    @NotEmpty(message = "Field can't be Empty")
    @NotBlank(message = "Field can't be Blank")
    @NotNull(message = "Field can't be Null")
    @Size(min = 7, max = 7, message = "should contain 7 symbols, ex. '33DD333' ")
    private String carNumber;
    @NotEmpty(message = "Field can't be Empty")
    @NotBlank(message = "Field can't be Blank")
    @NotNull(message = "Field can't be Null")
    @Size(min = 1, max = 20, message = "should contain 1 - 20 symbols")
    private String model;
    @NotEmpty(message = "Field can't be Empty")
    @NotBlank(message = "Field can't be Blank")
    @NotNull(message = "Field can't be Null")
    @Size(min = 1, max = 20, message = "should contain 1 - 20 symbols")
    private String color;
}
