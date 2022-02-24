package com.onik.spring.security.jwt.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UserApartmentsRequest {
    @NotEmpty
    @NotBlank
    @NotNull
    private List<UserApartmentRequest> userApartmentRequests;
}
