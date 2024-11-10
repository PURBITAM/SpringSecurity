package com.purbita.SpringSecurityPracticeJWTToken.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.purbita.SpringSecurityPracticeJWTToken.config.CustomUserDetails;
import com.purbita.SpringSecurityPracticeJWTToken.entity.User;
import com.purbita.SpringSecurityPracticeJWTToken.repository.UserRepository;



@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user!=null)
		{
			return new CustomUserDetails(user);
		}
		else
		{
			throw new UsernameNotFoundException("User not found");
		}
	}

}
