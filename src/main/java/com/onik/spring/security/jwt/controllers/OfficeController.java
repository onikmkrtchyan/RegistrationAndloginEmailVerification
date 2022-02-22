package com.onik.spring.security.jwt.controllers;

import com.onik.spring.security.jwt.dtos.request.OfficeCreateRequest;
import com.onik.spring.security.jwt.dtos.request.UserOfficeCreateRequest;
import com.onik.spring.security.jwt.service.office.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/office")
public class OfficeController {
    private final OfficeService officeService;

    @PostMapping
    public void create(@Valid @RequestBody OfficeCreateRequest officeCreateRequest) {
        officeService.create(officeCreateRequest);
    }

    @PostMapping("/addUser")
    public void addUserToOffice(@Valid @RequestBody UserOfficeCreateRequest userOfficeCreateRequest) {
        officeService.addUserToOffice(userOfficeCreateRequest);
    }
}