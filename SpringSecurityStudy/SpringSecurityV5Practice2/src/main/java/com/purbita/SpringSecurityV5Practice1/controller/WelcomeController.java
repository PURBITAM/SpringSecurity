package com.purbita.SpringSecurityV5Practice1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.SpringSecurityV5Practice1.config.AuthRequest;
import com.purbita.SpringSecurityV5Practice1.config.CustomJwtUtil;


@RestController
public class WelcomeController {
	
	@Autowired
	AuthenticationManager authenticationManger;
	
	@Autowired
	CustomJwtUtil customJwtUtil;
	
	@GetMapping("/public/welcome")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/private/welcome")
	public String welcome1() {
		return "private welcome";
	}
	
	@GetMapping("/private/admin/welcome")
	public String welcome2() {
		return "admin";
	}
	
	@GetMapping("/private/user/welcome")
	public String welcome3() {
		return "user";
	}
	
	
	@PostMapping("/public/login")
	public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest){
		Authentication authentication;
		
		authentication = authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			String token = customJwtUtil.createToken(authRequest.getUsername());
			return ResponseEntity.status(HttpStatus.OK).body(token);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
	}
}
