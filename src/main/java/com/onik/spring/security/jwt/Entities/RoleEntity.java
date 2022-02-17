package com.onik.spring.security.jwt.Entities;

import com.onik.spring.security.jwt.security.services.RoleEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public RoleEntity(RoleEnum roleEnum) {
        this.name=roleEnum;
    }
}