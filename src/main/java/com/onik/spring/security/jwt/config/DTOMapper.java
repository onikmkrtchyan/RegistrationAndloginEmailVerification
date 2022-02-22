package com.onik.spring.security.jwt.config;


import com.onik.spring.security.jwt.Entities.CarEntity;
import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import com.onik.spring.security.jwt.dtos.response.UserResponseWithDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    CarResponse toResponse(CarEntity carEntity);

    UserResponseWithDetails toUserDTOWithCars(UserEntity userEntity);

    //    @Mapping(source = "userOffice", target = "office")
    UserResponseWithDetails toDTO(UserEntity userEntity);
}