package com.onik.spring.security.jwt.service.office;

import com.onik.spring.security.jwt.dtos.request.OfficeCreateRequest;
import com.onik.spring.security.jwt.dtos.request.UserOfficeCreateRequest;

public interface OfficeService {
    void create(OfficeCreateRequest officeCreateRequest);

    void addUserToOffice(UserOfficeCreateRequest userOfficeCreateRequest);
}
