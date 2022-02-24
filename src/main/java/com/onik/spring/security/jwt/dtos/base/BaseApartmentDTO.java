package com.onik.spring.security.jwt.dtos.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BaseApartmentDTO {
    @NotEmpty
    @NotBlank
    @NotNull
    private String address;

    private int floor;

    private int number;
}
