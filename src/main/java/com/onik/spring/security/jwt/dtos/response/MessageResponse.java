package com.onik.spring.security.jwt.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    @NotEmpty(message = "Field can't be Empty")
    private String message;
}
