package com.purbita.SpringSecurityV5Practice1.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class CustomJwtUtil {
	
	String secret = "ASDCFREWTUASDCFREWTUASDCFREWTUASDCFREWTUASDCFREWTUASDCFREWTUASDCFREWTU";
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public <T>T extractClaims(String token, Function<Claims, T>claimsResolver){
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
	
	public String generateToken(Map<String, Object> claim, String subject) {
		return Jwts.builder().setClaims(claim).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256,secret).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public String createToken(String username) {
		Map<String, Object> claim = new HashMap<String, Object>();
		return generateToken(claim, username);
	}
	

}
