package com.jcode.fesol.security;

import static com.jcode.fesol.security.Constants.HEADER_AUTHORIZATION_KEY;
import static com.jcode.fesol.security.Constants.TOKEN_BEARER_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.jcode.fesol.user.model.UserAccount;



public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserAccount userAccount =  null;
			
	        if (request.getParameter("username") != null  && request.getParameter("password") != null) {
	        	userAccount = new UserAccount();              
	        	userAccount.setUsername(request.getParameter("username"));
	        	userAccount.setPassword(request.getParameter("password"));                
	        }
	        else {
	        	userAccount = new ObjectMapper().readValue(request.getInputStream(), UserAccount.class);
	        }

			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					userAccount.getUsername(), userAccount.getPassword(), Collections.emptyList()));
		}
		catch (IOException ex) {
			throw new RuntimeJsonMappingException(ex.getMessage());
		}
		catch (AuthenticationException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String token = TokenProvider.generateToken(auth);
		response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);
	}
}