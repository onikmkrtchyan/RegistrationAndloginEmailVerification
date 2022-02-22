package com.onik.spring.security.jwt.repository;

import com.onik.spring.security.jwt.Entities.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    @EntityGraph(attributePaths = {"user"})
    List<CarEntity> findByUserId(Long id);

    List<CarEntity> findAllByUserIdIn(List<Long> userId);

    boolean existsByCarNumber(String carNumber);

    @EntityGraph(attributePaths = {"user"})
    Page<CarEntity> findAllBy(Pageable pageable);


}
