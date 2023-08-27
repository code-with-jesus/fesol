package com.jcode.fesol.security;

import static com.jcode.fesol.security.Constants.AUTHORITIES_KEY;
import static com.jcode.fesol.security.Constants.HEADER_AUTHORIZATION_KEY;
import static com.jcode.fesol.security.Constants.TOKEN_BEARER_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        try {
            String authenticationHeader = request.getHeader(HEADER_AUTHORIZATION_KEY);
            if (authenticationHeader != null && authenticationHeader.startsWith(TOKEN_BEARER_PREFIX)) {
                String token = request.getHeader(HEADER_AUTHORIZATION_KEY).replace(TOKEN_BEARER_PREFIX, "");
                Claims claims = TokenProvider.getClaims(token);
                if (claims.get(AUTHORITIES_KEY) != null) {
                    SecurityContextHolder.getContext().setAuthentication(TokenProvider.getAuthentication(claims));
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

}
