package com.onik.spring.security.jwt.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserApartmentRequest {
    private Long userId;
    private List<Long> apartmentIds;
}
