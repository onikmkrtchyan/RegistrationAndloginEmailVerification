package com.onik.spring.security.jwt.dtos.base;

import com.onik.spring.security.jwt.security.services.RoleEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class BaseRoleDTO {

    @NotNull
    private RoleEnum name;
}