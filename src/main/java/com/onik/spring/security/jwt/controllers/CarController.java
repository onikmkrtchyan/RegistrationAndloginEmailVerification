package com.onik.spring.security.jwt.controllers;

import com.onik.spring.security.jwt.dtos.request.CarCreateRequest;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import com.onik.spring.security.jwt.security.services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/car")
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody CarCreateRequest carCreateRequest){
        Long id = carService.create(carCreateRequest);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<Page<CarResponse>> getAll() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<CarResponse> carResponses = carService.getAll(pageRequest);
        return ResponseEntity.ok(carResponses);
    }
}