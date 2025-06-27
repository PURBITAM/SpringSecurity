package com.purbita.SpringSecurityV6Practice8.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class CustomJwtUtil {
	
	String secret = "ASDFGHJKLOASDFGHJKLOASDFGHJKLOASDFGHJKLOASDFGHJKLOASDFGHJKLOASDFGHJKLO";
	
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
	
	public <T>T extractClaims(String token, Function<Claims,T>claimsResolver){
		Claims claim = extractAllClaims(token);
		return claimsResolver.apply(claim);
	}
	
	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(Map<String, Claims> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public String createToken(String username) {
		Map<String,Claims> claim = new HashMap<>();
		return generateToken(claim, username);
	}
	
	public Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
