package com.onik.spring.security.jwt.dtos.request;

import com.onik.spring.security.jwt.utils.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class SignupRequest {

    @NotEmpty
    @NotBlank
    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Valid
    @NotEmpty
    private List<RoleRequest> roles;

    @NotEmpty
    @NotBlank
    @NotNull
    @ValidPassword
    private String password;
}