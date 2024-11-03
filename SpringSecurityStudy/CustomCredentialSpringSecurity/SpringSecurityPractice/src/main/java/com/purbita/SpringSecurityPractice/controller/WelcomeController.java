package com.purbita.SpringSecurityPractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purbita.SpringSecurityPractice.entity.User;
import com.purbita.SpringSecurityPractice.repository.UserRepository;

@RestController
public class WelcomeController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/welcome")
	public String getWelcome()
	{
		List<User> user = userRepository.findAll();
		return "Welcome string returned"+user.toString();
	}
	
}
