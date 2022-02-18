package com.onik.spring.security.jwt.service;

import com.onik.spring.security.jwt.Entities.ApartmentEntity;
import com.onik.spring.security.jwt.Entities.UserApartmentEntity;
import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.dtos.request.ApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.UserApartmentRequest;
import com.onik.spring.security.jwt.dtos.request.UserApartmentsRequest;
import com.onik.spring.security.jwt.repository.ApartmentRepository;
import com.onik.spring.security.jwt.repository.UserApartmentRepository;
import com.onik.spring.security.jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {
    private final ApartmentRepository apartmentRepository;
    private final UserApartmentRepository userApartmentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long createApartment(ApartmentRequest apartmentRequest) {
        ApartmentEntity apartmentEntity = new ApartmentEntity();
        apartmentEntity.setFloor(apartmentRequest.getFloor());
        apartmentEntity.setNumber(apartmentRequest.getNumber());
        apartmentEntity.setAddress(apartmentRequest.getAddress());
        apartmentRepository.save(apartmentEntity);
        return apartmentEntity.getId();
    }

    @Override
    public void setUserApartment(UserApartmentRequest userApartmentRequest) {
        setApartment(userApartmentRequest);
    }

    @Override
    public void setUserApartments(UserApartmentsRequest userApartmentsRequest) {

        for (UserApartmentRequest userApartmentsRequest1 : userApartmentsRequest.getUserApartmentRequests()) {
            setApartment(userApartmentsRequest1);
        }
    }

    private void setApartment(UserApartmentRequest userApartmentsRequest1) {
        List<UserApartmentEntity> userApartmentEntities = new ArrayList<>();

        UserEntity userEntity = userRepository.getById(userApartmentsRequest1.getUserId());
        List<ApartmentEntity> apartmentEntities = apartmentRepository.findAllByIdIn(userApartmentsRequest1.getApartmentIds());

        for (ApartmentEntity apartmentEntity : apartmentEntities) {
            UserApartmentEntity userApartmentEntity = new UserApartmentEntity();
            userApartmentEntity.setApartment(apartmentEntity);
            userApartmentEntity.setUser(userEntity);
            userApartmentEntities.add(userApartmentEntity);
        }
        userApartmentRepository.saveAll(userApartmentEntities);
    }
}
