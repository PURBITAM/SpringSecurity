package com.purbita.SpringSecurityV6T4May25.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.SpringSecurityV6T4May25.config.AuthRequest;
import com.purbita.SpringSecurityV6T4May25.config.JwtUtil;
import com.purbita.SpringSecurityV6T4May25.models.Product;
import com.purbita.SpringSecurityV6T4May25.repo.ProductRepo;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/public")
public class PublicController {
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
    	Authentication authentication;
    	System.out.println("authRequest : "+authRequest.toString());
    	authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
    	if(authentication.isAuthenticated()) {
    		String token = jwtUtil.generateToken(authRequest.getUsername());
    		return ResponseEntity.status(HttpStatus.OK).body(token);
    	}
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no user found");
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> getProducts(@PathParam(value = "keyword") String keyword) {
    	List<Product> p = productRepo.findByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(keyword, keyword);
    	if(!p.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.OK).body(p);
    	}
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(p);
    }
    
}
