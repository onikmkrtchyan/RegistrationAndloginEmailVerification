package com.onik.spring.security.jwt.dtos.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BaseOfficeDTO {
    @NotNull(message  = "Field can't be Null")
    private Long number;

    @NotEmpty(message = "Field can't be Empty")
    @NotBlank(message = "Field can't be Blank")
    @NotNull(message  = "Field can't be Null")
    private String address;
}