package com.project.BankingApplication.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor

public class JwtTokenService {
    private static final String SECRET_KEY = "P9nQesIs4Zw+fzcoO2NMKZh1B9pRFFiHCQOIybcs4Bk=";

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, String> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .signWith(getSigningKey())
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 30 * 1000))
                .compact();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (!isTokenExpired(token) && username.equals(userDetails.getUsername()));
    }

    private Boolean isTokenExpired(String token) {
        Date exDate = extractClaims(token, Claims::getExpiration);
        return exDate.before(new Date());
    }
}
