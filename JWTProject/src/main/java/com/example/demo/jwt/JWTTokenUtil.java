package com.example.demo.jwt;

import java.util.Date;


import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.entites.User;
import com.example.demo.entites.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTTokenUtil {
	private static final org.jboss.logging.Logger LOGGER = LoggerFactory.logger(JWTTokenUtil.class);
	private static final long EXPIRE_DURATION = 2 * 60 *1000;
	
	@Value("${app.jwt.secret}")
	private String secretKey;
	
	public String generateAccessToken(UserDetailsImpl userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuer("CodeJava")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+ EXPIRE_DURATION))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512,secretKey)
				.compact();
	}
	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		}catch(ExpiredJwtException ex) {
			LOGGER.error("JWT exprored",ex);
		}catch(IllegalArgumentException ex) {
			LOGGER.error("Token is null,empty or has only white",ex);
		}catch(MalformedJwtException ex) {
			LOGGER.error("jwt is invalid", ex);
		}catch(UnsupportedJwtException ex) {
			LOGGER.error("JWT is not supported", ex);
		}catch(SignatureException ex) {
			LOGGER.error("Signature validation failed",ex);
		}
		return false;
	}
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	private Claims parseClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
	}
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
}
