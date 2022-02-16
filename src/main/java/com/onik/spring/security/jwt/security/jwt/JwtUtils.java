package com.onik.spring.security.jwt.security.jwt;


import java.util.Date;
import com.onik.spring.security.jwt.security.services.RefreshTokenService;
import com.onik.spring.security.jwt.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    private final RefreshTokenService refreshTokenService;

    @Value("${onik.app.jwtSecret}")
    private String jwtSecret;

    @Value("${onik.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${onik.app.jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;

    @Value("${tempTokenExpirationMs}")
    private long tempTokenExpirationMs;

    public JwtUtils(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    public String generateOneTimeToken(String username, String password) {
        LOGGER.info("generate one time token");
        Claims claims = Jwts.claims().setSubject(username);
        return buildJwt(claims, tempTokenExpirationMs, password);
    }


    /**
     * Disfigure password is used in jwt body
     * to invalidate all clients tokens of a user
     * when user reset or change his password
     * and all clients of a user should be signed out
     *
     * @param claims
     * @param jwtExpirationMs
     * @param password
     * @return
     */
    private String buildJwt(Claims claims, long jwtExpirationMs, String password) {
        LOGGER.info("build jwt token");
        claims.put("password", PasswordUtils.getDisfiguredPassword(password));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateTokenFromUsername(String username, boolean isRefreshToken) {
        int expirationMs = isRefreshToken ? jwtRefreshExpirationMs : jwtExpirationMs;
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    @Transactional
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            refreshTokenService.deleteByToken(authToken);
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
