package com.onik.spring.security.jwt.service;

import com.onik.spring.security.jwt.dtos.request.LoginRequest;
import com.onik.spring.security.jwt.dtos.request.SignupRequest;
import com.onik.spring.security.jwt.dtos.response.JwtResponse;
import com.onik.spring.security.jwt.dtos.response.MessageResponse;
import com.onik.spring.security.jwt.dtos.response.TokenRefreshResponse;
import com.onik.spring.security.jwt.security.services.UserDetailsImpl;


public interface UserLoginService {
    JwtResponse getLoginRequest(LoginRequest loginRequest);

    MessageResponse getSignupRequest(SignupRequest signupRequest);

    TokenRefreshResponse getRefreshTokenRequest(String request);

    String generateAndSaveRefreshToken(UserDetailsImpl userDetailsImpl);
}
