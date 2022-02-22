package com.onik.spring.security.jwt.security.services;

import com.onik.spring.security.jwt.Entities.CarEntity;
import com.onik.spring.security.jwt.config.DTOMapper;
import com.onik.spring.security.jwt.dtos.request.CarCreateRequest;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import com.onik.spring.security.jwt.exception.UserNotFoundException;
import com.onik.spring.security.jwt.repository.CarRepository;
import com.onik.spring.security.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final DTOMapper dtoMapper;
    private final UserRepository userRepository;

    @Transactional
    public Long create(CarCreateRequest carCreateRequest) {
        checkCarNumber(carCreateRequest);
        checkID(carCreateRequest);
        CarEntity carEntity = new CarEntity();
        carEntity.setModel(carCreateRequest.getModel());
        carEntity.setColor(carCreateRequest.getColor());
        carEntity.setCarNumber(carCreateRequest.getCarNumber());
        carEntity.setUser(userRepository.getById(carCreateRequest.getUserId()));
        carRepository.save(carEntity);
//        carEntity.setCarNumber("7637653");
        return carEntity.getId();
    }

    @Transactional(readOnly = true)
    public Page<CarResponse> getAll(PageRequest pageRequest) {
        Page<CarEntity> carEntities = carRepository.findAllBy(pageRequest);
        return carEntities.map(dtoMapper::toResponse);
    }

    private void checkID(CarCreateRequest carCreateRequest) {
        if (!userRepository.existsById(carCreateRequest.getUserId())) {
            throw new UserNotFoundException(carCreateRequest.getUserId());
        }
    }

    private void checkCarNumber(CarCreateRequest carCreateRequest) {
        if (carRepository.existsByCarNumber(carCreateRequest.getCarNumber())) {
            throw new RuntimeException(carCreateRequest.getCarNumber());
        }
    }

    public List<CarResponse> getCarsByUserId(Long id) {
        List<CarEntity> carEntities = carRepository.findByUserId(id);
        return carEntities.stream().map(dtoMapper::toResponse).collect(Collectors.toList());
    }
}
