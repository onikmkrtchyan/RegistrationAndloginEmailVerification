package com.onik.spring.security.jwt;

import com.onik.spring.security.jwt.Entities.RoleEntity;
import com.onik.spring.security.jwt.repository.RoleRepository;
import com.onik.spring.security.jwt.security.jwt.AuthTokenFilter;
import com.onik.spring.security.jwt.security.services.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootSecurityJwtApplication {
    private static final Logger LOGGER = LogManager.getLogger(SpringBootSecurityJwtApplication.class);
    private final RoleRepository roleRepository;
    @PostConstruct
    public void fillInRoleTable() {
        if(!roleRepository.exists(Example.of(new RoleEntity(RoleEnum.ROLE_USER)))) {
            roleRepository.save(new RoleEntity(RoleEnum.ROLE_USER));
        }
        if(!roleRepository.exists(Example.of(new RoleEntity(RoleEnum.ROLE_MODERATOR)))) {
            roleRepository.save(new RoleEntity(RoleEnum.ROLE_MODERATOR));
        }
        if(!roleRepository.exists(Example.of(new RoleEntity(RoleEnum.ROLE_ADMIN)))) {
            roleRepository.save(new RoleEntity(RoleEnum.ROLE_ADMIN));
        }
        LOGGER.info("Roles setups successfully");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
    }
}