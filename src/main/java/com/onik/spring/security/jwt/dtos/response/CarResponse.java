package com.onik.spring.security.jwt.dtos.response;

import com.onik.spring.security.jwt.dtos.base.BaseCarDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponse extends BaseCarDTO {
    private Long id;
}
