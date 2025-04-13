package com.purbita.SpringSecurityV6T4Codebase.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/auth/seller")
public class SellerController {

	@PostMapping("/product")
    public ResponseEntity<Object> postProduct() {
    	return null;
    }
	
    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts() {
    	return null;
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct() {
    	return null;
    }

    @PutMapping("/product")
    public ResponseEntity<?> putProduct() {
    	return null;
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProduct() {
    	return null;
    }

}
