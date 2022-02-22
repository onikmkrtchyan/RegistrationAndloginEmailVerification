package com.onik.spring.security.jwt.repository;

import com.onik.spring.security.jwt.Entities.UserOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOfficeRepository extends JpaRepository<UserOfficeEntity, Long> {

}
