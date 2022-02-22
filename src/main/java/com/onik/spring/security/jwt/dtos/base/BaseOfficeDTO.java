package com.onik.spring.security.jwt.dtos.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BaseOfficeDTO {
    @NotNull(message = "can't be null")
    private Long number;

    @NotNull(message = "can't be null")
    private String address;
}