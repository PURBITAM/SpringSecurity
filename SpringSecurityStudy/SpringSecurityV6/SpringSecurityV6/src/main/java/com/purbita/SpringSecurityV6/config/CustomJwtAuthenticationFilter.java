package com.purbita.SpringSecurityV6.config;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.purbita.SpringSecurityV6.service.UserAuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	CustomJwtUtil customJwtUtil;

	@Autowired
	UserAuthService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String header = request.getHeader("Authorization");
		if(header==null){
			filterChain.doFilter(request, response);
			return;
		}
		String token = null;
		System.out.println("header : "+header);
		if(header.startsWith("Bearer")){
			token = header.substring(7);
		}
		else{
			token = header;
		}
		String username = customJwtUtil.extractUsername(token);
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if(customJwtUtil.validateToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken authTOken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authTOken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authTOken);
			}
		}
		filterChain.doFilter(request, response);	
	}
}
