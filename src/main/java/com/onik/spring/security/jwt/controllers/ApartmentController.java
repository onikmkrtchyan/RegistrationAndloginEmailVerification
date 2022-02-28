package com.onik.spring.security.jwt.controllers;

import com.onik.spring.security.jwt.dtos.request.ApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.UserApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.UserApartmentsRequest;
import com.onik.spring.security.jwt.service.user.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
@RequestMapping("/apartment")
public class ApartmentController {
    private final UserDetailService userDetailService;

    @PostMapping()
    public ResponseEntity<Long> createApartment(@Valid @RequestBody ApartmentRequest apartmentRequest) {
        Long id = userDetailService.createApartment(apartmentRequest);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/set")
    public void set(@Valid @RequestBody UserApartmentRequest userApartmentRequest) {
        userDetailService.setUserApartment(userApartmentRequest);
    }

    @PostMapping("/setSeveral")
    public void set(@Valid @RequestBody UserApartmentsRequest userApartmentsRequest) {
        userDetailService.setUserApartments(userApartmentsRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userDetailService.delete(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ApartmentRequest apartmentRequest) {
        userDetailService.update(id, apartmentRequest);
    }
}