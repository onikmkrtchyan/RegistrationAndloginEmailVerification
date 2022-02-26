package com.onik.spring.security.jwt.service.car;

import com.onik.spring.security.jwt.Entities.CarEntity;
import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.config.DTOMapper;
import com.onik.spring.security.jwt.dtos.request.CarCreateRequest;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import com.onik.spring.security.jwt.exception.CarNotFoundException;
import com.onik.spring.security.jwt.exception.CarNumberAlreadyTakenException;
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
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final DTOMapper dtoMapper;
    private final UserRepository userRepository;

    @Transactional
    public Long create(CarCreateRequest carCreateRequest) {
        checkCarNumber(carCreateRequest);
        checkID(carCreateRequest);
        Long userId = carCreateRequest.getUserId();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        CarEntity carEntity = new CarEntity();
        carEntity.setModel(carCreateRequest.getModel());
        carEntity.setColor(carCreateRequest.getColor());
        carEntity.setCarNumber(carCreateRequest.getCarNumber());
        carEntity.setUser(user);
        carRepository.save(carEntity);
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
            throw new CarNumberAlreadyTakenException(carCreateRequest.getCarNumber());
        }
    }

    public List<CarResponse> getCarsByUserId(Long id) {
        List<CarEntity> carEntities = carRepository.findByUserId(id);
        return carEntities.stream().map(dtoMapper::toResponse).collect(Collectors.toList());
    }

    public void delete(Long id) {
        CarEntity car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        car.setDeleted(true);
        carRepository.save(car);
    }

    @Override
    public void update(Long id, CarCreateRequest carCreateRequest) {
        checkCarNumber(carCreateRequest);
        CarEntity car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        car.setCarNumber(carCreateRequest.getCarNumber());
        car.setColor(carCreateRequest.getColor());
        car.setModel(carCreateRequest.getModel());
        carRepository.save(car);
    }
}
