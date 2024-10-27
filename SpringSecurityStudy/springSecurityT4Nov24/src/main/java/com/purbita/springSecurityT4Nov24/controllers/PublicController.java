package com.purbita.springSecurityT4Nov24.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.springSecurityT4Nov24.config.JwtUtil;
import com.purbita.springSecurityT4Nov24.models.AuthenticationRequest;
import com.purbita.springSecurityT4Nov24.models.Product;
import com.purbita.springSecurityT4Nov24.models.User;
import com.purbita.springSecurityT4Nov24.service.ModelService;
import com.purbita.springSecurityT4Nov24.service.UserAuthService;

@RestController
@RequestMapping("/api/public")
public class PublicController {

	@Autowired
	ModelService modelService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserAuthService userAuthService;
    

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {

    	try {
    		System.out.println("try to authenticate");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            System.out.println("after try to authenticate");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String jwt = jwtUtil.generateToken(authRequest.getUsername());
        System.out.println("my jwt token : "+jwt);
        System.out.println(userAuthService.getUserByUsername(authRequest.getUsername()).toString());
        System.out.println("role : "+userAuthService.getUserByUsername(authRequest.getUsername()).getRoles().toArray()[0]);
        return ResponseEntity.ok(jwt);

    }

    @GetMapping("/product/search")
    public ResponseEntity<?> searchProducts(@RequestParam String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<Product> products = modelService.productRepoFindByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(keyword);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/users")
    public List<User> userfind() {
        List<User> users = modelService.userfind();
        System.out.println(users);
        return users;
    }
    
}
