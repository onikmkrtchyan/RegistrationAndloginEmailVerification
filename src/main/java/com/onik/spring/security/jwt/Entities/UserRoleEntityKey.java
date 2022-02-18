package com.onik.spring.security.jwt.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
class UserRoleEntityKey implements Serializable {

    @Column
    private Long userId;

    @Column
    private Long roleId;

}