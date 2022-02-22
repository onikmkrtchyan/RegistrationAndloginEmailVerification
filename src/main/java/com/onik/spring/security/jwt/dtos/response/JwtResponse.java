package com.onik.spring.security.jwt.dtos.response;

import com.onik.spring.security.jwt.utils.Instances;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private final List<String> roles;
    private String accessToken;
    private String tokenType = Instances.BEARER;
    private String refreshToken;
    private Long id;
    private String username;
    private String email;

    public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}