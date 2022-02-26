package com.onik.spring.security.jwt.security.jwt;

import com.onik.spring.security.jwt.security.services.UserDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.onik.spring.security.jwt.utils.Instances.BEARER;

public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LogManager.getLogger(AuthTokenFilter.class);

    @Value("${onik.app.jwtSecret}")
    private String jwtSecret;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    public AuthTokenFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                LOGGER.info("Token signature is validated");
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                validateJwtToken(jwt, userDetails.getPassword());//This used for email verification , makes token one time
//                LOGGER.info("Token is validated");
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                LOGGER.info("Authentication is set");
            }
        } catch (Exception e) {
            LOGGER.info("Cannot set user authentication: {}" + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER)) {
            return headerAuth.substring(BEARER.length() + 1);
        }
        return null;
    }

    public String getDisfiguredPasswordFromToken(String token) {
        LOGGER.info("get disfigured password from token ");
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("password", String.class);
    }
}
