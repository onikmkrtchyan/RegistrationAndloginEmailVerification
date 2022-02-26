package com.onik.spring.security.jwt.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String role_not_in_db) {
        super(role_not_in_db);
    }
}
