package com.purbita.SpringSecurityV6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.SpringSecurityV6.config.AuthRequest;
import com.purbita.SpringSecurityV6.config.CustomJwtUtil;
import com.purbita.SpringSecurityV6.entity.Users;
import com.purbita.SpringSecurityV6.repository.UserRepository;

@RestController
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomJwtUtil customJwtUtil;

	@GetMapping("/")
    public List<Users> home() {return userRepository.findAll();}
	
	@GetMapping("/welcome")
    public String welcome() {return "welcome";}
	
//	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/user/welcome")
	public String UserAccess(){return "only user can access";}
	
	@GetMapping("/admin/welcome")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public String AdminAccess(){return "only admin can access";}
	
	@GetMapping("/adminuser/welcome")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public String AdminUserAccess(){return "only admin and user can access";}
	
	@GetMapping("/client/welcome")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public String ClientAccess(){return "only client can access";}
	
	@PutMapping("/login")
	public ResponseEntity<String> Login(@RequestBody AuthRequest authRequest)
	{
		Authentication authentication;
		System.out.println("authrequest : "+authRequest);
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nope");
		}
		if(authentication.isAuthenticated())
		{
			String token = customJwtUtil.generateToken(authRequest.getUsername());
			return ResponseEntity.status(HttpStatus.OK).body(token);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("user not found");
		}
		
	}
}
