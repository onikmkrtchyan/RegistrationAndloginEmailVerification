package com.onik.spring.security.jwt.dtos.request;

import com.onik.spring.security.jwt.dtos.base.BaseCarDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CarCreateRequest extends BaseCarDTO {
    @NotNull
    private Long userId;
}
