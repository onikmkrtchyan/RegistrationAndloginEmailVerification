package com.onik.spring.security.jwt.controllers;

import javax.validation.Valid;

import com.onik.spring.security.jwt.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onik.spring.security.jwt.dtos.request.LoginRequest;
import com.onik.spring.security.jwt.dtos.request.SignupRequest;
import com.onik.spring.security.jwt.dtos.response.JwtResponse;
import com.onik.spring.security.jwt.dtos.response.MessageResponse;
import com.onik.spring.security.jwt.dtos.response.TokenRefreshResponse;
import com.onik.spring.security.jwt.security.services.RefreshTokenService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserLoginService userLoginService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        MessageResponse messageResponse = userLoginService.getSignupRequest(signUpRequest);
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/signing")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = userLoginService.getLoginRequest(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestHeader(value = "Authorization") String refreshToken) {
        TokenRefreshResponse tokenRefreshResponse = userLoginService.getRefreshTokenRequest(refreshToken);
        return ResponseEntity.ok(tokenRefreshResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestHeader(value = "Authorization") String refreshToken) {
        MessageResponse messageResponse = refreshTokenService.deleteByRefreshToken(refreshToken);
        return ResponseEntity.ok(messageResponse);
    }
}