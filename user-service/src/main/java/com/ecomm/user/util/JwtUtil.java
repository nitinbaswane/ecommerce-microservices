package com.ecomm.user.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final SecretKey signingKey;
    private final long accessTokenExpirationMs;

    public JwtUtil(@Value("${app.jwt.secrets}") String signingKey, @Value("${app.jwt.expiration-ms}") long accessTokenExpirationMs) {
        this.signingKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(signingKey)
        );
        this.accessTokenExpirationMs = accessTokenExpirationMs;
    }

    public String generateToken(UserDetails userDetails) {

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("email", userDetails.getUsername())
                .claim("roles", roles)
                .issuedAt(new Date(now))
                .expiration(
                        new Date(
                             now + accessTokenExpirationMs
                        )
                )
                .signWith(signingKey)
                .compact();
    }


    public Jws<Claims> validateToken(String token){
        return Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(token);
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = validateToken(token).getBody();
        return Long.valueOf(claims.getSubject());
    }





}
