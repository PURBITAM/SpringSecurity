package com.purbita.SpringSecurityV6T4May25.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.purbita.SpringSecurityV6T4May25.models.User;

public class CustomUserDetails implements UserDetails {
	
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	
	

	public CustomUserDetails(User user) {
		username = user.getUsername();
		password = user.getPassword();
		 String[] stringArray = user.getRoles().stream().map(Enum::name).toArray(String[]::new);
//		System.out.println("user.getRoles().toArray()[0]).split(\",\") : "+((String) user.getRoles().toArray()[0]).split(","));
		authorities = Arrays.stream((stringArray))
//		 authorities = Arrays.stream(("CONSUMER").split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
		System.out.println("authorities : "+authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
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
