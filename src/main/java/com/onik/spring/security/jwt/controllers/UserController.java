package com.onik.spring.security.jwt.controllers;


import com.onik.spring.security.jwt.dtos.response.UserResponseWithCarList;
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
    public ResponseEntity<UserResponseWithCarList> getCarsById(@PathVariable Long id) {
        UserResponseWithCarList userResponseWithCarList = userService.getWithCars(id);
        return ResponseEntity.ok(userResponseWithCarList);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseWithCarList>> getAllUsersWithData() {
        List<UserResponseWithCarList> userResponseWithCarList = userService.getAllUsersWithData();
        return ResponseEntity.ok(userResponseWithCarList);
    }
}