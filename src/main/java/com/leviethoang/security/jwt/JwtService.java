package com.leviethoang.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String JWT_SECRET = "50645367566B59703373367638792F423F4528482B4D6251655468576D5A7134";

    public String getGeneratedToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return genarateTokenForUser(claims, username);
    }

    private String genarateTokenForUser(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+(100000+90*30)))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[]keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsernameFromToken(String theToken){
        return extractClaim(theToken , Claims::getSubject);
    }

    public Date extractExpirationTimeFromToken(String theToken){
        return extractClaim(theToken, Claims::getExpiration);
    }

    public Boolean validateToken(String theToken , UserDetails userDetails){
        final String username = extractUsernameFromToken(theToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired((theToken)));
    }

    private <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String theToken){
        return extractExpirationTimeFromToken(theToken)
                .before(new Date());
    }




}
