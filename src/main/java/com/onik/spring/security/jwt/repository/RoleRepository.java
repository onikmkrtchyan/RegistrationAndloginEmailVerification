package com.onik.spring.security.jwt.repository;

import java.util.Optional;

import com.onik.spring.security.jwt.Entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onik.spring.security.jwt.security.services.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
