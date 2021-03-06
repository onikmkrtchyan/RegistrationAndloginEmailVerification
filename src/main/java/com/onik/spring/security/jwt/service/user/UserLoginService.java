package com.onik.spring.security.jwt.service.user;

import com.onik.spring.security.jwt.dtos.request.*;
import com.onik.spring.security.jwt.dtos.response.JwtResponse;
import com.onik.spring.security.jwt.dtos.response.MessageResponse;
import com.onik.spring.security.jwt.dtos.response.TokenRefreshResponse;
import com.onik.spring.security.jwt.security.services.UserDetailsImpl;

public interface UserLoginService {
    JwtResponse getLoginRequest(LoginRequest loginRequest);

    MessageResponse getSignupRequest(SignupRequest signupRequest);

    TokenRefreshResponse getRefreshTokenRequest(String request);

    String generateAndSaveRefreshToken(UserDetailsImpl userDetailsImpl);

    Long create(SignupEmailRequest signupEmailRequest);

    void updatePassword(CreatePasswordUserDTO createPasswordUserDTO);

    void update(Long id, SignupRequest signupRequest);
}
