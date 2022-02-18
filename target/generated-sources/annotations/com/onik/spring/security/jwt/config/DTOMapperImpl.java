package com.onik.spring.security.jwt.config;

import com.onik.spring.security.jwt.Entities.ApartmentEntity;
import com.onik.spring.security.jwt.Entities.CarEntity;
import com.onik.spring.security.jwt.Entities.RoleEntity;
import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.dtos.response.ApartmentResponse;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import com.onik.spring.security.jwt.dtos.response.RoleResponse;
import com.onik.spring.security.jwt.dtos.response.UserResponseWithCarList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-18T18:29:50+0400",
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
        userResponseWithCarList.setRoles( roleEntityListToRoleResponseList( userEntity.getRoles() ) );
        userResponseWithCarList.setCars( carEntityListToCarResponseList( userEntity.getCars() ) );
        userResponseWithCarList.setApartments( apartmentEntityListToApartmentResponseList( userEntity.getApartments() ) );

        return userResponseWithCarList;
    }

    @Override
    public UserResponseWithCarList toDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponseWithCarList userResponseWithCarList = new UserResponseWithCarList();

        userResponseWithCarList.setUsername( userEntity.getUsername() );
        userResponseWithCarList.setEmail( userEntity.getEmail() );
        userResponseWithCarList.setRoles( roleEntityListToRoleResponseList( userEntity.getRoles() ) );
        userResponseWithCarList.setCars( carEntityListToCarResponseList( userEntity.getCars() ) );
        userResponseWithCarList.setApartments( apartmentEntityListToApartmentResponseList( userEntity.getApartments() ) );

        return userResponseWithCarList;
    }

    protected RoleResponse roleEntityToRoleResponse(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleResponse roleResponse = new RoleResponse();

        roleResponse.setName( roleEntity.getName() );

        return roleResponse;
    }

    protected List<RoleResponse> roleEntityListToRoleResponseList(List<RoleEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleResponse> list1 = new ArrayList<RoleResponse>( list.size() );
        for ( RoleEntity roleEntity : list ) {
            list1.add( roleEntityToRoleResponse( roleEntity ) );
        }

        return list1;
    }

    protected List<CarResponse> carEntityListToCarResponseList(List<CarEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CarResponse> list1 = new ArrayList<CarResponse>( list.size() );
        for ( CarEntity carEntity : list ) {
            list1.add( toResponse( carEntity ) );
        }

        return list1;
    }

    protected ApartmentResponse apartmentEntityToApartmentResponse(ApartmentEntity apartmentEntity) {
        if ( apartmentEntity == null ) {
            return null;
        }

        ApartmentResponse apartmentResponse = new ApartmentResponse();

        apartmentResponse.setAddress( apartmentEntity.getAddress() );
        apartmentResponse.setFloor( apartmentEntity.getFloor() );
        apartmentResponse.setNumber( apartmentEntity.getNumber() );

        return apartmentResponse;
    }

    protected List<ApartmentResponse> apartmentEntityListToApartmentResponseList(List<ApartmentEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ApartmentResponse> list1 = new ArrayList<ApartmentResponse>( list.size() );
        for ( ApartmentEntity apartmentEntity : list ) {
            list1.add( apartmentEntityToApartmentResponse( apartmentEntity ) );
        }

        return list1;
    }
}
