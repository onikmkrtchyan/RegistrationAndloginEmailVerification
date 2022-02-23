package com.onik.spring.security.jwt.dtos.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class UserResponseWithDetails {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private List<RoleResponse> roles;

    private List<CarResponse> cars;

    private List<ApartmentResponse> apartments;

//    private OfficeResponse office;//this used for getting info Without userOffice(in JSON)

    private UserOfficeResponse userOffice;
}
