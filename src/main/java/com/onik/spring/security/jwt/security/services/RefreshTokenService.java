package com.onik.spring.security.jwt.security.services;

import com.onik.spring.security.jwt.dtos.response.MessageResponse;
import com.onik.spring.security.jwt.exception.RefreshTokenNotFoundException;
import com.onik.spring.security.jwt.utils.Instances;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.onik.spring.security.jwt.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private static final Logger LOGGER = LogManager.getLogger(RefreshTokenService.class);
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MessageResponse deleteByRefreshToken(String refreshToken) {
        refreshToken = refreshToken.substring(Instances.BEARER.length()+1);
        if (!refreshTokenRepository.existsByToken(refreshToken))
            throw new RefreshTokenNotFoundException(refreshToken);
        else
            deleteByToken(refreshToken);
        LOGGER.info("Token is validated");
        return new MessageResponse("Log Out Successfully");
    }

    public void deleteByToken(String authToken) {
        refreshTokenRepository.deleteByToken(authToken);
    }
}
