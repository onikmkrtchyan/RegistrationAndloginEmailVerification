package com.onik.spring.security.jwt.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    public UserEntity(String username, String email, String password, List<RoleEntity> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_apartment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "apartment_id")
    )
    private List<ApartmentEntity> apartments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CarEntity> cars;
}
