package com.onik.spring.security.jwt.service.office;

import com.onik.spring.security.jwt.Entities.OfficeEntity;
import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.Entities.UserOfficeEntity;
import com.onik.spring.security.jwt.dtos.request.OfficeCreateRequest;
import com.onik.spring.security.jwt.dtos.request.UserOfficeCreateRequest;
import com.onik.spring.security.jwt.repository.OfficeRepository;
import com.onik.spring.security.jwt.repository.UserOfficeRepository;
import com.onik.spring.security.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;
    private final UserOfficeRepository userOfficeRepository;
    private final UserRepository userRepository;

    @Override
    public void create(OfficeCreateRequest officeCreateRequest) {
        OfficeEntity officeEntity = new OfficeEntity();
        officeEntity.setAddress(officeCreateRequest.getAddress());
        officeEntity.setNumber(officeCreateRequest.getNumber());
        officeRepository.save(officeEntity);
    }

    @Override
    public void addUserToOffice(UserOfficeCreateRequest userOfficeCreateRequest) {
        OfficeEntity officeEntity = officeRepository.findById(userOfficeCreateRequest.getOfficeId())
                .orElseThrow(() -> new RuntimeException("Office not found"));
        UserEntity userEntity = userRepository.findById(userOfficeCreateRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserOfficeEntity userOfficeEntity = new UserOfficeEntity();
        userOfficeEntity.setOffice(officeEntity);
        userOfficeEntity.setUser(userEntity);
        userOfficeEntity.setIsRemote(userOfficeCreateRequest.getIsRemote());
        userOfficeRepository.save(userOfficeEntity);
    }
}