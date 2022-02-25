package com.onik.spring.security.jwt.controllers;

import com.onik.spring.security.jwt.dtos.request.ApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.OfficeCreateRequest;
import com.onik.spring.security.jwt.dtos.request.UserOfficeCreateRequest;
import com.onik.spring.security.jwt.service.office.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
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

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        officeService.delete(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody OfficeCreateRequest officeCreateRequest){
        officeService.update(id,officeCreateRequest);
    }
}