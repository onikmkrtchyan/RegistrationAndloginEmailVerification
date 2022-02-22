package com.onik.spring.security.jwt.dtos.base;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BaseUserOfficeDTO {
    @NotNull(message = "can't be null")
    private Long userId;
    @NotNull(message = "can't be null")
    private Long officeId;
    @NotNull(message = "can't be null")
    private Boolean isRemote;
}