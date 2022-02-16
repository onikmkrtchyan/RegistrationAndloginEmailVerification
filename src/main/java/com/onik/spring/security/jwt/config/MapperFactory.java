package com.onik.spring.security.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperFactory {

    @Bean
    public DTOMapper dtoMapper() {
        return DTOMapper.INSTANCE;
    }

}
