package com.onik.spring.security.jwt.service;

import com.onik.spring.security.jwt.Entities.RefreshTokenEntity;
import com.onik.spring.security.jwt.Entities.RoleEntity;
import com.onik.spring.security.jwt.Entities.UserRoleEntity;
import com.onik.spring.security.jwt.repository.UserRoleRepository;
import com.onik.spring.security.jwt.security.services.RoleEnum;
import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.dtos.request.LoginRequest;
import com.onik.spring.security.jwt.dtos.request.SignupRequest;
import com.onik.spring.security.jwt.dtos.response.JwtResponse;
import com.onik.spring.security.jwt.dtos.response.MessageResponse;
import com.onik.spring.security.jwt.dtos.response.TokenRefreshResponse;
import com.onik.spring.security.jwt.exception.TokenRefreshException;
import com.onik.spring.security.jwt.exception.UserNotFoundException;
import com.onik.spring.security.jwt.repository.RefreshTokenRepository;
import com.onik.spring.security.jwt.repository.RoleRepository;
import com.onik.spring.security.jwt.repository.UserRepository;
import com.onik.spring.security.jwt.security.jwt.JwtUtils;
import com.onik.spring.security.jwt.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsService userDetailsService;
    private final UserRoleRepository userRoleRepository;

    @Override
    public JwtResponse getLoginRequest(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateTokenFromUsername(userDetailsImpl.getUsername(), false);

        String refreshTokenJWT = generateAndSaveRefreshToken(userDetailsImpl);

        List<String> roles = userDetailsImpl.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, refreshTokenJWT, userDetailsImpl.getId(), userDetailsImpl.getUsername(), userDetailsImpl.getEmail(),roles);
}

    @Override
    public MessageResponse getSignupRequest(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleEntity>  roleEntities = getRoleEntity(strRoles);

        UserEntity user = new UserEntity(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), new ArrayList<>(roleEntities));

        userRepository.save(user);

//        List<UserRoleEntity> userRoleEntities = new ArrayList<>();
//        for (RoleEntity roleEntity : roleEntities) {
//            UserRoleEntity userRoleEntity = new UserRoleEntity();
//            userRoleEntity.setUser(userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UserNotFoundException(user.getId())));
//            userRoleEntity.setRole(roleEntity);
//            userRoleEntities.add(userRoleEntity);
//        }
//        userRoleRepository.saveAll(userRoleEntities);

        return new MessageResponse("User registered successfully!");
    }

    @Override
    @Transactional
    public TokenRefreshResponse getRefreshTokenRequest(String oldRefreshToken) {
        oldRefreshToken = oldRefreshToken.substring(7);
        if (refreshTokenRepository.existsByToken(oldRefreshToken)) {
            String username = jwtUtils.getUserNameFromJwtToken(oldRefreshToken);
            refreshTokenRepository.deleteByToken(oldRefreshToken);
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
            String newAccessToken = jwtUtils.generateTokenFromUsername(username, false);
            String newRefreshToken = generateAndSaveRefreshToken(userDetailsImpl);
            return new TokenRefreshResponse(newAccessToken, newRefreshToken);
        } else
            throw new TokenRefreshException(oldRefreshToken, "Time Expired for this refresh token: Please Login again");
    }

    @Override
    public String generateAndSaveRefreshToken(UserDetailsImpl userDetailsImpl) {
        String jwtRefreshToken = jwtUtils.generateTokenFromUsername(userDetailsImpl.getUsername(), true);
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setUser(userRepository.findById(userDetailsImpl.getId())
                .orElseThrow(() -> new UserNotFoundException(userDetailsImpl.getId())));
        refreshTokenEntity.setToken(jwtRefreshToken);
        refreshTokenRepository.save(refreshTokenEntity);
        return jwtRefreshToken;
    }

    private Set<RoleEntity>  getRoleEntity(Set<String> strRoles) {
        Set<RoleEntity> roleEntities = new HashSet<>();
        if (strRoles == null) {
            RoleEntity userRoleEntity = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roleEntities.add(userRoleEntity);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase().trim()) {
                    case "admin":
                        RoleEntity adminRoleEntity = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roleEntities.add(adminRoleEntity);
                        break;
                    case "mod":
                        RoleEntity modRoleEntity = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roleEntities.add(modRoleEntity);
                        break;
                    default:
                        RoleEntity userRoleEntity = roleRepository.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roleEntities.add(userRoleEntity);
                }
            });
        }
        return roleEntities;
    }
}