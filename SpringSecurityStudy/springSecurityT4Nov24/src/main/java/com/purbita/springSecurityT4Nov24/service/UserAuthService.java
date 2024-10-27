package com.purbita.springSecurityT4Nov24.service;

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

import com.purbita.springSecurityT4Nov24.config.JwtUtil;
import com.purbita.springSecurityT4Nov24.config.UserInfoDetails;
import com.purbita.springSecurityT4Nov24.models.User;
import com.purbita.springSecurityT4Nov24.repo.UserRepo;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
	
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
		User user = userRepo.findByUsername(username);
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
//        return user.map(UserInfoDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
//        
        if(user != null)
        {
        	System.out.println("Hiii");
        	System.out.println("user.getRoles().toArray()[0] : "+user.getRoles().toArray()[0]);
        	List<GrantedAuthority> roles = new ArrayList<>(); 
        	roles.add(new SimpleGrantedAuthority(user.getRoles().toArray()[0].toString()));
        	
//        	UserInfoDetails userInfoDetails = new UserInfoDetails(user);
        	System.out.println("roles : "+roles);
        	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
        }
        else
        {
        	System.out.println("okkkk");
        	return null;
        }
        
//		Optional<User> user = userRepo.findByUsername(username);
//		if(user.isPresent())
//		{
//			System.out.println("loadUserByUsername : " + user.get().toString());
//			//System.out.println("(UserDetails) user.get() : " + (UserDetails) user.get());
//			return (UserDetails) user.get();
//		}
//		else
//		{
//			System.out.println("Username not found");
//			throw new UsernameNotFoundException("Username not found");
//		}
	//------------------------------------------------------------------------------------------------		
		
//		User user = userRepo.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                AuthorityUtils.createAuthorityList(user.getRoles().)
//        );
	}
	
//	public String login(String username, String password) {
//		System.out.println("before authenticationManager.authenticate");
//		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        System.out.println("after authenticationManager.authenticate");
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        System.out.println("userDetails : ");
//       // System.out.println("jwtUtil.generateToken(userDetails) : " + jwtUtil.generateToken(userDetails));
//        return jwtUtil.generateToken(userDetails);
//    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
