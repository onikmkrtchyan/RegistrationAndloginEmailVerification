package com.onik.spring.security.jwt.Entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_apartment")
public class UserApartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY/*,optional = false*/)
    private UserEntity user;

//    @JoinColumn(name = "apartment_id")
    @Where(clause = "deleted = false")
    @ManyToOne(fetch = FetchType.LAZY/*,optional = false*/)
    private ApartmentEntity apartment;
}