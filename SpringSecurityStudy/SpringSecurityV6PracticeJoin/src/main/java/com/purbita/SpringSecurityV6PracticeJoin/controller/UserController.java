package com.purbita.SpringSecurityV6PracticeJoin.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.SpringSecurityV6PracticeJoin.config.CustomUserDetails;
import com.purbita.SpringSecurityV6PracticeJoin.entity.Users;
import com.purbita.SpringSecurityV6PracticeJoin.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/roles/{id}")
	public Object roles(@PathVariable Integer id) {
		Optional<Users> user = userRepository.findById(id);
		System.out.println("user.get() : "+user.get());
		CustomUserDetails customUserDetails = new CustomUserDetails(user.get());
		System.out.println("customUserDetails : user3 : "+customUserDetails.getAuthorities());
		return customUserDetails.getAuthorities();
	}
	
}
