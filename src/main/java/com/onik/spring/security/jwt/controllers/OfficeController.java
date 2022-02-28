package com.onik.spring.security.jwt.controllers;

import com.onik.spring.security.jwt.dtos.request.OfficeCreateRequest;
import com.onik.spring.security.jwt.dtos.request.UserOfficeCreateRequest;
import com.onik.spring.security.jwt.service.office.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/office")
public class OfficeController {
    private final OfficeService officeService;

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping
    public void create(@Valid @RequestBody OfficeCreateRequest officeCreateRequest) {
        officeService.create(officeCreateRequest);
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping("/setUser")
    public void setUser(@Valid @RequestBody UserOfficeCreateRequest userOfficeCreateRequest) {
        officeService.addUserToOffice(userOfficeCreateRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        officeService.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody OfficeCreateRequest officeCreateRequest) {
        officeService.update(id, officeCreateRequest);
    }
}