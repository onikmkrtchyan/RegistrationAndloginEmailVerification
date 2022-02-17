package com.onik.spring.security.jwt.dtos.request;

import com.onik.spring.security.jwt.utils.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Valid
    @NotEmpty
    private List<RoleRequest> role;

    @NotBlank
    @ValidPassword
    private String password;
}