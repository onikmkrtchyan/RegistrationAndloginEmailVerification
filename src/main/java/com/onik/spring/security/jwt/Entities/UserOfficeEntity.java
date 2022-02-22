package com.onik.spring.security.jwt.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "user_office")
public class UserOfficeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(unique = true)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OfficeEntity office;

    @Column(nullable = false)
    private Boolean isRemote;
}