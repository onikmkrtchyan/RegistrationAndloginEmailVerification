package com.onik.spring.security.jwt.dtos.response;

import com.onik.spring.security.jwt.dtos.base.BaseCarDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CarResponse extends BaseCarDTO {
    private Long id;
}
