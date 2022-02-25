package com.onik.spring.security.jwt.service.user;

import com.onik.spring.security.jwt.Entities.UserEntity;
import org.cryptacular.spec.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class UserEntitySpec {

    public static Specification<UserEntity> getAllWithRoles(List<Long> longs) {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            root.fetch("roles", JoinType.LEFT);
            criteriaQuery.distinct(true);
            return root.in(longs);
        });
    }

    public static Specification<UserEntity> getAllWithCars() {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            root.fetch("cars", JoinType.LEFT);
            criteriaQuery.distinct(true);
            return root.isNotNull();
        });
    }

    public static Specification<UserEntity> getAllWithApartments(List<Long> longs) {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            root.fetch("userApartment", JoinType.LEFT).fetch("apartment", JoinType.LEFT);
            Predicate predicate = criteriaBuilder.equal(root.join("userApartment", JoinType.LEFT).get("apartment").get("deleted"), false);
            criteriaQuery.distinct(true);
            root.in(longs);
            return predicate;
        });
    }

    public static Specification<UserEntity> getAllWithOffice(List<Long> longs) {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            root.fetch("userOffice").fetch("office");
//            Predicate predicate = criteriaBuilder.equal(root.join("userOffice").get("office").get("deleted"), false);
            criteriaQuery.distinct(true);
            return root.in(longs);
        });
    }
}
