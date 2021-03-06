package com.onik.spring.security.jwt.dtos.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserOfficeResponse {
    private String isRemote;
    private OfficeResponse office;
}
