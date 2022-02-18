package com.onik.spring.security.jwt.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserApartmentRequest {
    private Long userId;
    private Long apartmentId;
}
