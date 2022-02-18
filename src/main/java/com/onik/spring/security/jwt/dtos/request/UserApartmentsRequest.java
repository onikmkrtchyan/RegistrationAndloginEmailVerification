package com.onik.spring.security.jwt.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserApartmentsRequest {
    private List<UserApartmentRequest> userApartmentRequests;
}
