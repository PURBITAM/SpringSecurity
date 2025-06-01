package com.purbita.SpringSecurityV6PracticeJoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.purbita.SpringSecurityV6PracticeJoin.config.CustomUserDetails;
import com.purbita.SpringSecurityV6PracticeJoin.entity.Users;
import com.purbita.SpringSecurityV6PracticeJoin.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);
		return new CustomUserDetails(user);
	}

}
