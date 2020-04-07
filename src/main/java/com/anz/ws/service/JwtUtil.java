package com.anz.ws.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.anz.ws.security.JwtConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	public String extractUsernameFromToken(String token) {

		return extractClaimFromToken(token, Claims::getAudience);

	}

	public Date extractExpirationDateFromToken(String token) {

		return extractClaimFromToken(token, Claims::getExpiration);

	}

	public <T> T extractClaimFromToken(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaimsFromToken(token);

		return claimsResolver.apply(claims);

	}

	private Claims extractAllClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(JwtConstants.TOKEN_SECRET).parseClaimsJws(token).getBody();

	}

	private Boolean isTokenExpired(String token) {

		final Date expiration = extractExpirationDateFromToken(token);

		return expiration.before(new Date());

	}

	public String generateToken(UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<>();

		return createToken(claims, userDetails.getUsername());

	}

	private String createToken(Map<String, Object> claims, String audience) {

		String jwtToken = Jwts.builder().setAudience(audience).setSubject(JwtConstants.sub)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JwtConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, JwtConstants.TOKEN_SECRET).compact();
		return jwtToken;

	}

	public Boolean validateToken(String token, UserDetails userDetails) {

		final String username = extractUsernameFromToken(token);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

}
