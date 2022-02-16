package com.onik.spring.security.jwt.service.mail.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegContentDTO {

    private String fullName;

    private String email;

    private String token;
}
