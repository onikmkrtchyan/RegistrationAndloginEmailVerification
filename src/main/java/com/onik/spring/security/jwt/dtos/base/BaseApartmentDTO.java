package com.onik.spring.security.jwt.dtos.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseApartmentDTO {
    private String address;
    private int floor;
    private int number;
}
