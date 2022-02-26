package com.onik.spring.security.jwt.service.user;

import com.onik.spring.security.jwt.Entities.RefreshTokenEntity;
import com.onik.spring.security.jwt.Entities.RoleEntity;
import com.onik.spring.security.jwt.Entities.UserEntity;
import com.onik.spring.security.jwt.dtos.request.*;
import com.onik.spring.security.jwt.dtos.response.JwtResponse;
import com.onik.spring.security.jwt.dtos.response.MessageResponse;
import com.onik.spring.security.jwt.dtos.response.TokenRefreshResponse;
import com.onik.spring.security.jwt.exception.EmailAlreadyTakenException;
import com.onik.spring.security.jwt.exception.TokenRefreshException;
import com.onik.spring.security.jwt.exception.UserNotFoundException;
import com.onik.spring.security.jwt.exception.UsernameAlreadyTakenException;
import com.onik.spring.security.jwt.repository.RefreshTokenRepository;
import com.onik.spring.security.jwt.repository.RoleRepository;
import com.onik.spring.security.jwt.repository.UserRepository;
import com.onik.spring.security.jwt.security.jwt.JwtUtils;
import com.onik.spring.security.jwt.security.services.RoleEnum;
import com.onik.spring.security.jwt.security.services.UserDetailsImpl;
import com.onik.spring.security.jwt.service.mail.EmailSenderService;
import com.onik.spring.security.jwt.utils.Instances;
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
import java.util.List;
import java.util.stream.Collectors;

import static com.onik.spring.security.jwt.utils.PasswordUtils.generate;

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
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;

    private static void comparePasswords(CreatePasswordUserDTO createPasswordUserDTO) {
        if (createPasswordUserDTO.getPassword().equals(createPasswordUserDTO.getRepeatedPassword()))
            return;
        throw new RuntimeException("Password mismatch");
    }

    @Override
    @Transactional
    public Long create(SignupEmailRequest signupEmailRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signupEmailRequest.getUsername());
        userEntity.setEmail(signupEmailRequest.getEmail());
        userEntity.setRoles(getRoleEntity(signupEmailRequest.getRoles()));
        String oneTimePassword = generate();
        userEntity.setPassword(oneTimePassword);
        userRepository.save(userEntity);
        emailSenderService.sendCreatePasswordEmail(signupEmailRequest, oneTimePassword);
        return userEntity.getId();
    }

    @Override
    @Transactional
    public void updatePassword(CreatePasswordUserDTO createPasswordUserDTO) {
        comparePasswords(createPasswordUserDTO);
        updatePassword(createPasswordUserDTO.getPassword());
    }

    @Override
    @Transactional
    public void update(Long id, SignupRequest signupRequest) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new UsernameAlreadyTakenException(signupRequest.getUsername());
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAlreadyTakenException(signupRequest.getEmail());
        }
        user.setEmail(signupRequest.getEmail());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        List<RoleRequest> strRoles = signupRequest.getRoles();
        List<RoleEntity> roleEntities = getRoleEntity(strRoles);
        user.setRoles(roleEntities);

        userRepository.save(user);
    }

    private void updatePassword(String password) {
        UserDetailsImpl usi = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository
                .findByUsername(usi.getUsername())
                .orElseThrow(() -> new RuntimeException("username"));
        userEntity.setPassword(passwordEncoder.encode(password));
        userRepository.save(userEntity);
    }

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

        return new JwtResponse(jwt, refreshTokenJWT, userDetailsImpl.getId(), userDetailsImpl.getUsername(), userDetailsImpl.getEmail(), roles);
    }

    @Override
    public MessageResponse getSignupRequest(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        List<RoleEntity> roleEntities = getRoleEntity(signupRequest.getRoles());

        UserEntity user = new UserEntity();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setRoles(roleEntities);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

    @Override
    @Transactional
    public TokenRefreshResponse getRefreshTokenRequest(String oldRefreshToken) {
        oldRefreshToken = oldRefreshToken.substring(Instances.BEARER.length()+1);
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

    private List<RoleEntity> getRoleEntity(List<RoleRequest> roles) {
        List<RoleEntity> roleEntities = new ArrayList<>();
        if (roles == null) {
            RoleEntity userRoleEntity = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roleEntities.add(userRoleEntity);
        } else {
            roles.forEach(role -> {
                RoleEntity modRoleEntity = roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roleEntities.add(modRoleEntity);
            });
        }
        return roleEntities;
    }
}