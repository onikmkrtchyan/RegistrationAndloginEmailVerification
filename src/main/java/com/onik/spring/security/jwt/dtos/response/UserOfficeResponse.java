package com.onik.spring.security.jwt.dtos.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserOfficeResponse {
    @NotEmpty(message = "Field can't be Empty")
    private String isRemote;
    @NotEmpty(message = "Field can't be Empty")
    private OfficeResponse office;
}
