package com.onik.spring.security.jwt.repository;

import com.onik.spring.security.jwt.Entities.UserApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApartmentRepository extends JpaRepository<UserApartmentEntity, Long>,
        JpaSpecificationExecutor<UserApartmentEntity> {
}
