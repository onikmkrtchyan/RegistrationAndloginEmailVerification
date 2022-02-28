package com.onik.spring.security.jwt.controllers;


import com.onik.spring.security.jwt.dtos.request.SignupRequest;
import com.onik.spring.security.jwt.dtos.response.UserResponseWithDetails;
import com.onik.spring.security.jwt.security.services.UserDetailsServiceImpl;
import com.onik.spring.security.jwt.service.user.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsServiceImpl userService;
    private final UserLoginService userLoginService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/details")
    public ResponseEntity<UserResponseWithDetails> getCarsById(@PathVariable Long id) {
        UserResponseWithDetails userResponseWithDetails = userService.getWithDetails(id);
        return ResponseEntity.ok(userResponseWithDetails);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseWithDetails>> getAllUsersWithData() {
        List<UserResponseWithDetails> userResponseWithDetails = userService.getAllUsersWithData();
        return ResponseEntity.ok(userResponseWithDetails);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody SignupRequest signupRequest) {
        userLoginService.update(id, signupRequest);
    }
}