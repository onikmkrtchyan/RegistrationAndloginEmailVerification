package com.onik.spring.security.jwt.controllers;

import com.onik.spring.security.jwt.dtos.request.ApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.UserApartmentRequest;
import com.onik.spring.security.jwt.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apartment")
public class ApartmentController {
    private final UserLoginService userLoginService;

    @PostMapping()
    public ResponseEntity<Long> registerUserEmail(@Valid @RequestBody ApartmentRequest apartmentRequest){
        Long id =  userLoginService.createApartment(apartmentRequest);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/setApartment")
    public void setApartment(@Valid @RequestBody UserApartmentRequest userApartmentRequest){
        userLoginService.setUserApartment(userApartmentRequest);
    }

}
