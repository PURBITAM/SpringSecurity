package com.purbita.SpringSecurityPracticeJWTToken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.SpringSecurityPracticeJWTToken.config.JwtUtil;
import com.purbita.SpringSecurityPracticeJWTToken.entity.AuthRequest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "javainuseapi")
public class UserController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public String getWelcome(){return "jwt returned successfully";}
	
	@GetMapping("/user")
	public String getUserWelcome(){return "jwt user returned successfully";}
	
	@GetMapping("/admin")
	public String getAdminWelcome(){return "jwt admin returned successfully";}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}
		catch (Exception e) {
			throw new Exception("User not found");
		}
		return jwtUtil.generateToken(authRequest.getUsername());
	}
}

