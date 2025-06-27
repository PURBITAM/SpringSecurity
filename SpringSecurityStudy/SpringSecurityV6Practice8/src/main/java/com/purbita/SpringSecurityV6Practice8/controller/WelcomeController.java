package com.purbita.SpringSecurityV6Practice8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.SpringSecurityV6Practice8.config.AuthRequest;
import com.purbita.SpringSecurityV6Practice8.config.CustomJwtUtil;
import com.purbita.SpringSecurityV6Practice8.repository.UserRepository;

@RestController
public class WelcomeController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository r;
	
	@Autowired
	CustomJwtUtil customJwtUtil;

	@GetMapping("/public")
	public String m1() {
		return "public";
	}
	
	@GetMapping("/private/admin")
	public String m12() {
		return "admin";
	}
	
	@GetMapping("/private/user")
	public String m3() {
		return "user";
	}
	
	@PutMapping("/public/login")
	public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest) {
		System.out.println(authRequest.toString());
		System.out.println(r.findByUsername(authRequest.getUsername()));
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		System.out.println(authentication.isAuthenticated());
		if(authentication.isAuthenticated()) {
			System.out.println("inside");
			String token = customJwtUtil.createToken(authRequest.getUsername());
			return ResponseEntity.status(HttpStatus.OK).body(token);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
	}
	
}
