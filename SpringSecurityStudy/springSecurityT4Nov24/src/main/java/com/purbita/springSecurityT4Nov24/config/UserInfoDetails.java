package com.purbita.springSecurityT4Nov24.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.purbita.springSecurityT4Nov24.models.Role;
import com.purbita.springSecurityT4Nov24.models.User;


public class UserInfoDetails implements UserDetails {
	
	private String username;
	private String password;
	private List<GrantedAuthority> roles;
	
	

	public UserInfoDetails(User user) {
		
		username = user.getUsername();
		password = user.getPassword();
		roles = Arrays.stream(((String) user.getRoles().toArray()[0]).split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
