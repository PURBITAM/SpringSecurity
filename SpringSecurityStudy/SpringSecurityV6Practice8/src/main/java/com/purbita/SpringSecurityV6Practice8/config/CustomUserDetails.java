package com.purbita.SpringSecurityV6Practice8.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.purbita.SpringSecurityV6Practice8.entity.Roles;
import com.purbita.SpringSecurityV6Practice8.entity.Users;

public class CustomUserDetails implements UserDetails {
	
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public CustomUserDetails(Users user) {
		username = user.getUsername();
		password = user.getPassword();
		//System.out.println(user);
		authorities = user.getRoles().stream().map(Roles::getName)
				//Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		System.out.println("authorities : "+authorities);
		return authorities;
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
