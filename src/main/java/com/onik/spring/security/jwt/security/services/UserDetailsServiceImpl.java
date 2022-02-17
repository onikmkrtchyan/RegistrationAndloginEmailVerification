package com.onik.spring.security.jwt.security.services;

import com.onik.spring.security.jwt.config.DTOMapper;
import com.onik.spring.security.jwt.dtos.base.BaseCarDTO;
import com.onik.spring.security.jwt.dtos.response.UserResponseWithCarList;
import com.onik.spring.security.jwt.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final CarService carService;
    private final DTOMapper dtoMapper;

    public UserDetailsServiceImpl(UserRepository userRepository, CarService carService, DTOMapper dtoMapper) {
        this.userRepository = userRepository;
        this.carService = carService;
        this.dtoMapper = dtoMapper;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    @Transactional(readOnly = true)
    public UserResponseWithCarList getWithCars(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        UserResponseWithCarList userResponseWithCarList = dtoMapper.toUserDTOWithCars(userEntity);
        List<BaseCarDTO> baseCarDTOList = carService.getCarsByUserId(id);
        userResponseWithCarList.setCarResponses(baseCarDTOList);
        return userResponseWithCarList;
    }
}
