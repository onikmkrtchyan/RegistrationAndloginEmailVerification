package com.onik.spring.security.jwt.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreatePasswordUserDTO {

    @NotEmpty
    @NotBlank
    @NotNull
    private String password;
    @NotEmpty
    @NotBlank
    @NotNull
    private String repeatedPassword;
}
