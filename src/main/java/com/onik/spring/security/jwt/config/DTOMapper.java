package com.onik.spring.security.jwt.config;


import com.onik.spring.security.jwt.Entities.CarEntity;
import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.dtos.request.BaseCarDTO;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import com.onik.spring.security.jwt.dtos.response.UserResponseWithCarList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);
    CarResponse toResponse(CarEntity carEntity);

    UserResponseWithCarList toUserDTOWithCars(UserEntity userEntity);

    BaseCarDTO toBaseCarDTO(CarEntity carEntity);
}