package com.easybuy.service.securrity;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtTokenProvider jwtToken;
	@Autowired
	private CustomUserDetailsService customUser;
	private final SecretKey key = Keys.hmacShaKeyFor(
	        "SecretKeyForJwtAuthenticationSecretKey123456"
	                .getBytes());
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		  String token=getToken(request);
		  if(StringUtils.hasText(token)&&jwtToken.validateToken(token)) {
			  String email=jwtToken.getEmailFromToken(token);
			  UserDetails userdetails=customUser.loadUserByUsername(email);
			  UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
			  SecurityContextHolder.getContext().setAuthentication(authentication);
		  }
		  filterChain.doFilter(request,response);
		
	}
	private String getToken(HttpServletRequest request) {
		String token=request.getHeader("Authorization");
		if(StringUtils.hasText(token)&&token.startsWith("Bearer ")) {
			return token.substring(7,token.length());
			
		}
		else
			return null;
	}
  
}
