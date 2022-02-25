package com.onik.spring.security.jwt.service.car;


import com.onik.spring.security.jwt.dtos.request.CarCreateRequest;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CarService {
    Long create(CarCreateRequest carCreateRequest);

    Page<CarResponse> getAll(PageRequest pageRequest);

    void delete(Long id);

    void update(Long id, CarCreateRequest carCreateRequest);
}
