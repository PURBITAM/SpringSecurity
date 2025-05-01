package com.purbita.SpringSecurityV6T4May25.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.purbita.SpringSecurityV6T4May25.config.CustomUserDetails;
import com.purbita.SpringSecurityV6T4May25.models.User;
import com.purbita.SpringSecurityV6T4May25.repo.UserRepo;

@Service
public class UserAuthService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	public User loadUserByUserId(Integer id)
	{
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent())
		{
			return user.get();
		}
		else
			throw new UsernameNotFoundException("Username not found");
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepo.findByUsername(username);
		System.out.println("user : "+user.get().toString());
		System.out.println(" role : "+ (user.get().getRoles()));
		String[] stringArray = user.get().getRoles().stream().map(Enum::name).toArray(String[]::new);
		System.out.println(" role : "+ Arrays.toString(stringArray));
		if(user.isPresent())
		{
			return new CustomUserDetails(user.get());
		}
		else
			throw new UsernameNotFoundException("Username not found");
	}

}
