package com.onik.spring.security.jwt.security.services;

import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.config.DTOMapper;
import com.onik.spring.security.jwt.dtos.response.CarResponse;
import com.onik.spring.security.jwt.dtos.response.UserResponseWithDetails;
import com.onik.spring.security.jwt.exception.UserNotFoundException;
import com.onik.spring.security.jwt.repository.UserRepository;
import com.onik.spring.security.jwt.service.user.UserEntitySpec;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final CarService carService;
    private final DTOMapper dtoMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    @Transactional(readOnly = true)
    public UserResponseWithDetails getWithDetails(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        UserResponseWithDetails userResponseWithDetails = dtoMapper.toUserDTOWithCars(userEntity);
        List<CarResponse> carResponses = carService.getCarsByUserId(id);
        userResponseWithDetails.setCars(carResponses);
        return userResponseWithDetails;
    }

    @Transactional(readOnly = true)
    public List<UserResponseWithDetails> getAllUsersWithData() {
        List<UserEntity> userEntityList =
                userRepository.findAll(UserEntitySpec.getAllWithCars());
        userRepository.findAll(UserEntitySpec.getAllWithRoles());
        userRepository.findAll(UserEntitySpec.getAllWithApartments());
        userRepository.findAll(UserEntitySpec.getAllWithOffice());

        return userEntityList.stream().map(dtoMapper::toDTO).collect(Collectors.toList());
    }
}
