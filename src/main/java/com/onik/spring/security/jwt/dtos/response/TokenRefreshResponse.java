package com.onik.spring.security.jwt.dtos.response;


import com.onik.spring.security.jwt.utils.Instances;
import lombok.*;

@Getter
@Setter
public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = Instances.BEARER;

    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
