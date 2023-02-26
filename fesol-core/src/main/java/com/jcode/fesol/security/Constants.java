package com.jcode.fesol.security;

public class Constants {

	// Spring Security

	public static final String REGISTER_URL = "/register";
	public static final String PROCESSES_URL = "/api/auth/login";
	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT
	
	public static final String AUTHORITIES_KEY = "authorities";

	public static final String ISSUER_INFO = "https://www.fesol.co/";
	public static final String SECRET_KEY = "dGhpc19zdHJpbmdfX3MzY3IzdF9pc19mb3JfdGVzdGluZ19vbmx5";
	public static final long TOKEN_EXPIRATION_TIME = 3_600_000; // 1 hour
	
	private Constants() {
		// Hide constructor
	}

}