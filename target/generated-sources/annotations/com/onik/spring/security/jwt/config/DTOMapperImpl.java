package com.onik.spring.security.jwt.config;

import com.onik.spring.security.jwt.Entities.CarEntity;
import com.onik.spring.security.jwt.Entities.RoleEntity;
import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.dtos.base.BaseCarDTO;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import com.onik.spring.security.jwt.dtos.response.UserResponseWithCarList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-17T12:32:11+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class DTOMapperImpl implements DTOMapper {

    @Override
    public CarResponse toResponse(CarEntity carEntity) {
        if ( carEntity == null ) {
            return null;
        }

        CarResponse carResponse = new CarResponse();

        carResponse.setCarNumber( carEntity.getCarNumber() );
        carResponse.setModel( carEntity.getModel() );
        carResponse.setColor( carEntity.getColor() );
        carResponse.setId( carEntity.getId() );

        return carResponse;
    }

    @Override
    public UserResponseWithCarList toUserDTOWithCars(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponseWithCarList userResponseWithCarList = new UserResponseWithCarList();

        userResponseWithCarList.setUsername( userEntity.getUsername() );
        userResponseWithCarList.setEmail( userEntity.getEmail() );
        List<RoleEntity> list = userEntity.getRole();
        if ( list != null ) {
            userResponseWithCarList.setRole( new ArrayList<RoleEntity>( list ) );
        }

        return userResponseWithCarList;
    }

    @Override
    public BaseCarDTO toBaseCarDTO(CarEntity carEntity) {
        if ( carEntity == null ) {
            return null;
        }

        BaseCarDTO baseCarDTO = new BaseCarDTO();

        baseCarDTO.setCarNumber( carEntity.getCarNumber() );
        baseCarDTO.setModel( carEntity.getModel() );
        baseCarDTO.setColor( carEntity.getColor() );

        return baseCarDTO;
    }
}
