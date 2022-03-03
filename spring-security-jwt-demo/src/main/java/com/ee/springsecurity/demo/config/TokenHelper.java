package com.ee.springsecurity.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenHelper {
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    private static final String AUDIENCE_WEB = "web";
    private static final String ISSUER = "everest";

    private final ApplicationProperties applicationProperties;

    public String generateToken(String username) {
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(username)
                .setAudience(AUDIENCE_WEB)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(SIGNATURE_ALGORITHM, applicationProperties.getJwtSecret())
                .compact();
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring("Bearer ".length());
        }
        return null;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final Claims claims = getAllClaimsFromToken(token);
        final String username = claims.getSubject();
        final Date expiration = claims.getExpiration();
        return username != null && username.equals(userDetails.getUsername()) && new Date().before(expiration);
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(applicationProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
