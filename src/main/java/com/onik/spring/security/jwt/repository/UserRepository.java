package com.onik.spring.security.jwt.repository;

import java.util.List;
import java.util.Optional;

import com.onik.spring.security.jwt.Entities.CarEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.onik.spring.security.jwt.Entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    UserEntity getById(Long userId);

    @EntityGraph(attributePaths = {"cars"})
    List<UserEntity> findAll();

    @EntityGraph(attributePaths = {"role"})
    List<UserEntity> getAllBy();
}
