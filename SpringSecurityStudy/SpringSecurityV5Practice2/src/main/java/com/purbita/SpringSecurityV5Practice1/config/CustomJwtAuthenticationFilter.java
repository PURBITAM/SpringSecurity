package com.purbita.SpringSecurityV5Practice1.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.purbita.SpringSecurityV5Practice1.service.UserAuthService;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	CustomJwtUtil customJwtUtil;
	
	@Autowired
	UserAuthService userAuthService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		
		if(header==null) {
			filterChain.doFilter(request, response);
			return;
		}
		String token;
		if(header!=null && header.startsWith("Bearer") ) {
			token = header.substring(7);
		}
		else {
			token = header;
		}
		
		String username = customJwtUtil.extractUsername(token);
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userAuthService.loadUserByUsername(username);
			if(customJwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
