package com.onik.spring.security.jwt.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "apartment")
public class ApartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int floor;

    private int number;

    @Column(nullable = false)
    private String address;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(referencedColumnName = "id")
//    private UserEntity user;
}
