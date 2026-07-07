package com.easybuy.service.securrity;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.easybuy.service.Exceptions.APIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	private final SecretKey key = Keys.hmacShaKeyFor(
	        "SecretKeyForJwtAuthenticationSecretKey123456"
	                .getBytes());
public String generateToken(Authentication authentication) {
	String email=authentication.getName();
	Date currentDate=new Date();
	Date expireDate=new Date(currentDate.getTime()+3600000);
	String token = Jwts.builder()
	        .subject(email)
	        .issuedAt(currentDate)
	        .expiration(expireDate)
	        .signWith(key)
	        .compact();
	return token;
}
public String getEmailFromToken(String token) {
	 Claims claims = Jwts.parser()
	            .verifyWith(key)
	            .build()
	            .parseSignedClaims(token)
	            .getPayload();

	    return claims.getSubject();
	
}
public boolean validateToken(String token) {
	   try {
	        Jwts.parser()
	            .verifyWith(key)
	            .build()
	            .parseSignedClaims(token);

	        return true;
	    } catch (Exception e) {
	        throw new APIException("Token Issue: " + e.getMessage());
	    }
}
}
