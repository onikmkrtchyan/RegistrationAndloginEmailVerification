package com.onik.spring.security.jwt.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Where(clause="deleted=false")
@Table(name = "office")
public class OfficeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private String address;

    private boolean deleted = Boolean.FALSE;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office")
    private List<UserOfficeEntity> userOffices;
}
