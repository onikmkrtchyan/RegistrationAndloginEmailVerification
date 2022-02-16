package com.onik.spring.security.jwt.dtos.request;

import com.onik.spring.security.jwt.utils.ValidPassword;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {

    @ValidPassword
    private String oldPassword;

    @ValidPassword
    private String newPassword1;

    @ValidPassword
    private String newPassword2;
}
