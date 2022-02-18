package com.onik.spring.security.jwt.service;

import com.onik.spring.security.jwt.dtos.request.ApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.UserApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.UserApartmentsRequest;

public interface UserDetailService {

    Long createApartment(ApartmentRequest apartmentRequest);

    void setUserApartment(UserApartmentRequest userApartmentRequest);

    void setUserApartments(UserApartmentsRequest userApartmentsRequest);

}
