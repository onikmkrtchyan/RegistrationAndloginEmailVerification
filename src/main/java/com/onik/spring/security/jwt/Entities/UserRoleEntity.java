package com.onik.spring.security.jwt.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_role")
public class UserRoleEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @EmbeddedId
    private UserRoleEntityKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserEntity user;

}