package com.onik.spring.security.jwt.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "user")
@Where(clause = "deleted=false")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserApartmentEntity> userApartment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CarEntity> cars;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserOfficeEntity userOffice;

    public UserEntity(String username, String email, String password, List<RoleEntity> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserOfficeEntity getUserOffice() {
        if (isNull(userOffice)) return null;
        if (isNull(userOffice.getOffice()) || userOffice.getOffice().isDeleted()) return null;
        return userOffice;
    }
//
//    public List<UserApartmentEntity> getUserApartment() {
//        for (UserApartmentEntity userApartmentEntity : userApartment) {
//            if (isNull(userApartmentEntity.getApartment()) || userApartmentEntity.getApartment().isDeleted())
//                return null;
//        }
//        return userApartment;
//    }
}
