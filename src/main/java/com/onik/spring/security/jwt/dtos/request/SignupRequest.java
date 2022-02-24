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

    @NotEmpty(message = "Field can't be Empty")
    @NotBlank(message = "Field can't be Blank")
    @NotNull(message  = "Field can't be Null")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Valid
    @NotEmpty
    private List<RoleRequest> roles;

    @NotEmpty(message = "Field can't be Empty")
    @NotBlank(message = "Field can't be Blank")
    @NotNull(message  = "Field can't be Null")
    @ValidPassword
    private String password;
}