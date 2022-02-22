package com.onik.spring.security.jwt.repository;

import com.onik.spring.security.jwt.Entities.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {

}
