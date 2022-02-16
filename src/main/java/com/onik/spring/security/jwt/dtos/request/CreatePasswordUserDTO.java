package com.onik.spring.security.jwt.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreatePasswordUserDTO {

    @NotEmpty
    private String password;
    @NotEmpty
    private String repeatedPassword;
}
