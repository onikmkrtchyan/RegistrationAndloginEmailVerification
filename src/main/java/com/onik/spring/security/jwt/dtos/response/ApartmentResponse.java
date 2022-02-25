package com.onik.spring.security.jwt.dtos.response;

import com.onik.spring.security.jwt.dtos.base.BaseApartmentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ApartmentResponse extends BaseApartmentDTO {
    private Long id;
}
