package com.onik.spring.security.jwt.dtos.response;

import com.onik.spring.security.jwt.dtos.base.BaseOfficeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OfficeResponse extends BaseOfficeDTO {
    private Long id;
}
