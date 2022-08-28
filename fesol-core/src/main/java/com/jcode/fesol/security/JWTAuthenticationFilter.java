package com.jcode.fesol.security;

import static com.jcode.fesol.security.Constants.HEADER_AUTHORIZACION_KEY;
import static com.jcode.fesol.security.Constants.ISSUER_INFO;
import static com.jcode.fesol.security.Constants.SECRET_KEY;
import static com.jcode.fesol.security.Constants.TOKEN_BEARER_PREFIX;
import static com.jcode.fesol.security.Constants.TOKEN_EXPIRATION_TIME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.jcode.fesol.user.model.UserAccount;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserAccount userAccount = new ObjectMapper().readValue(request.getInputStream(), UserAccount.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userAccount.getUsername(), userAccount.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeJsonMappingException(e.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

		String token = Jwts.builder()
				.setSubject(((User)auth.getPrincipal()).getUsername())
				.setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.signWith(secretKey)
				.compact();
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
	}
}