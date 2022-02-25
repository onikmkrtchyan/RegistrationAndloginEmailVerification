package com.onik.spring.security.jwt.controllers;

import com.onik.spring.security.jwt.dtos.request.CarCreateRequest;
import com.onik.spring.security.jwt.dtos.request.SignupRequest;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import com.onik.spring.security.jwt.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
@RequestMapping(value = "/car")
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody CarCreateRequest carCreateRequest) {
        Long id = carService.create(carCreateRequest);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<Page<CarResponse>> getAll() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<CarResponse> carResponses = carService.getAll(pageRequest);
        return ResponseEntity.ok(carResponses);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        carService.delete(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CarCreateRequest carCreateRequest){
        carService.update(id,carCreateRequest);
    }
}