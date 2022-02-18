package com.onik.spring.security.jwt.controllers;

import com.onik.spring.security.jwt.Entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

public class UserEntitySpec {
    public static Specification<UserEntity> getAllWithCars() {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            root.fetch("cars", JoinType.LEFT);
            return root.isNotNull();
        });
    }

    public static Specification<UserEntity> getAllWithRoles() {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            root.fetch("roles", JoinType.LEFT);
            return root.isNotNull();
        });
    }

    public static Specification<UserEntity> getAllWithApartments() {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            root.fetch("apartments", JoinType.LEFT);
            return root.isNotNull();
        });
    }
}
