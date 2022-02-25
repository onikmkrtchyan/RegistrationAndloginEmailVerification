package com.onik.spring.security.jwt.dtos.response;

import com.onik.spring.security.jwt.dtos.base.BaseRoleDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleResponse extends BaseRoleDTO {

    private Long id;
}
