package com.purbita.SpringSecurityV5Practice1.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.purbita.SpringSecurityV5Practice1.config.CustomUserDetails;
import com.purbita.SpringSecurityV5Practice1.entity.Users;
import com.purbita.SpringSecurityV5Practice1.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);
		return new CustomUserDetails(user);
	}

}
