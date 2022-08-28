package com.jcode.fesol.security;

public class Constants {

	// Spring Security

	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT

	public static final String ISSUER_INFO = "https://www.fesol.co/";
	public static final String SECRET_KEY = "VDBwJDNjcjN0IQ==";
	public static final long TOKEN_EXPIRATION_TIME = 3_600_000; // 1 hour
	
	private Constants() {
		// Hide constructor
	}

}