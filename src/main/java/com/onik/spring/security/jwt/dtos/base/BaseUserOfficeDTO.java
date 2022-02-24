package com.onik.spring.security.jwt.dtos.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BaseUserOfficeDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Long officeId;
    @NotNull
    private Boolean isRemote;
}