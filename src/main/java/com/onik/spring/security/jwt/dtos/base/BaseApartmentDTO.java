package com.onik.spring.security.jwt.dtos.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BaseApartmentDTO {
    @NotNull
    private String address;

    private int floor;

    private int number;
}
