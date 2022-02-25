package com.onik.spring.security.jwt.service.user;

import com.onik.spring.security.jwt.dtos.request.ApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.UserApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.UserApartmentsRequest;

public interface UserDetailService {

    Long createApartment(ApartmentRequest apartmentRequest);

    void setUserApartment(UserApartmentRequest userApartmentRequest);

    void setUserApartments(UserApartmentsRequest userApartmentsRequest);

    void delete(Long id);

    void update(Long id, ApartmentRequest apartmentRequest);
}
