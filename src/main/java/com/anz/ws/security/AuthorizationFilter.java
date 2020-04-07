package com.anz.ws.security;

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

import com.anz.ws.service.JwtUtil;
import com.anz.ws.service.UserAuthenticationService;

import io.jsonwebtoken.JwtException;



@Component
public class AuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	 UserAuthenticationService userAuthService;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, JwtException {
		
		final String authHeader=request.getHeader("Authorization");
		String userName=null;
		String jwtToken=null;
		
		if(authHeader!=null&&authHeader.startsWith("Bearer "))
		{
			jwtToken=authHeader.substring(7);
			userName=jwtUtil.extractUsernameFromToken(jwtToken);
		}
		
		if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails= userAuthService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(jwtToken, userDetails))
			{
				UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
				
			}
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
