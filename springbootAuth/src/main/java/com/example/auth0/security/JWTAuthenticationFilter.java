package com.example.auth0.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.example.auth0.config.SecurityProperties;
import com.example.auth0.domain.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
	private SecurityProperties securityProperties;
	
	private AuthenticationManager authenticationManager;

	private ApplicationUser creds;
	
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SecurityProperties securityProperties) {
        this.authenticationManager = authenticationManager;
		this.securityProperties = securityProperties;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
			creds = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);

            return authenticationManager.authenticate(
                    new JwtAuthentication(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>(),
                            creds.getUserProperties())
            );
        } catch (IOException e) {
        	log.error("Error while create authenticationManager",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

    	
        Builder with = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + securityProperties.getExpirationTime()));
        addUserProperties(with);

        String token = with.sign(HMAC512(securityProperties.getSecret().getBytes()));
        
        res.addHeader(securityProperties.getHeaderString(), securityProperties.getTokenPrefix() + token);
    }

	private void addUserProperties(Builder with) {
		Properties userProperties = creds.getUserProperties();
        for (Object key : userProperties.keySet()) {
			Object value = userProperties.get(key);
			with.withClaim(key.toString(), value.toString());
		}
	}
}