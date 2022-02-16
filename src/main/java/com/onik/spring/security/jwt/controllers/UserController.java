package com.onik.spring.security.jwt.controllers;


import com.onik.spring.security.jwt.dtos.response.UserResponseWithCarList;
import com.onik.spring.security.jwt.security.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsServiceImpl userService;

    //get all cars by user_id
    @GetMapping("/{id}/cars")
    public ResponseEntity<UserResponseWithCarList> getCarsById(@PathVariable Long id) {
        UserResponseWithCarList userResponseWithCarList = userService.getWithCars(id);
        return ResponseEntity.ok(userResponseWithCarList);
    }
}