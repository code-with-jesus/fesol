package com.jcode.fesol.security;

import static com.jcode.fesol.security.Constants.AUTHORITIES_KEY;
import static com.jcode.fesol.security.Constants.ISSUER_INFO;
import static com.jcode.fesol.security.Constants.SECRET_KEY;
import static com.jcode.fesol.security.Constants.TOKEN_EXPIRATION_TIME;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenProvider {

    private TokenProvider() {
        // Hide constructor
    }

    public static String generateToken(Authentication auth) {
        final String authorities =
            auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        final SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

        return Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername()).claim(AUTHORITIES_KEY, authorities)
            .signWith(secretKey).setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
            .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME)).compact();
    }

    public static Claims getClaims(final String token) {
        return Jwts.parserBuilder().setSigningKey(Decoders.BASE64.decode(SECRET_KEY)).build().parseClaimsJws(token)
            .getBody();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(final Claims claims) {
        final Collection<SimpleGrantedAuthority> authorities =
            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }

}
