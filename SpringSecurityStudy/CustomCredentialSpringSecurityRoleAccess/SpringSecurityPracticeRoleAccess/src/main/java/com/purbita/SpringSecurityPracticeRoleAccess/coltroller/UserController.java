package com.purbita.SpringSecurityPracticeRoleAccess.coltroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/welcome")
	public String getWelcome()
	{
	 return "returned successfully";
	}
	
	@GetMapping("/user")
	public String getUserWelcome()
	{
	return "user returned successfully";
	}
	
	@GetMapping("/admin")
	public String getAdminWelcome()
	{
	return "admin returned successfully";
	}
}
