package com.onik.spring.security.jwt.controllers;


import com.onik.spring.security.jwt.dtos.response.UserResponseWithDetails;
import com.onik.spring.security.jwt.security.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsServiceImpl userService;

    //get user details by user_id
    @GetMapping("/{id}/details")
    public ResponseEntity<UserResponseWithDetails> getCarsById(@PathVariable Long id) {
        UserResponseWithDetails userResponseWithDetails = userService.getWithDetails(id);
        return ResponseEntity.ok(userResponseWithDetails);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseWithDetails>> getAllUsersWithData() {
        List<UserResponseWithDetails> userResponseWithDetails = userService.getAllUsersWithData();
        return ResponseEntity.ok(userResponseWithDetails);
    }
}