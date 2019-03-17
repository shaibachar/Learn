package com.example.auth0.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.auth0.config.SecurityProperties;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	
	private AuthenticationManager authManager;
	private SecurityProperties securityProperties;

	public JWTAuthorizationFilter(AuthenticationManager authManager,SecurityProperties securityProperties) {
		super(authManager);
		this.authManager = authManager;
		this.securityProperties = securityProperties;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(securityProperties.getHeaderString());

		if (header == null || !header.startsWith(securityProperties.getTokenPrefix())) {
			chain.doFilter(req, res);
			return;
		}

		JwtAuthentication authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private JwtAuthentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(securityProperties.getHeaderString());
		if (token != null) {
			// parse the token.
			DecodedJWT verify = JWT.require(Algorithm.HMAC512(securityProperties.getSecret().getBytes())).build()
					.verify(token.replace(securityProperties.getTokenPrefix(), ""));
			String user = verify.getSubject();
			Properties p = getProperties(verify);

			if (user != null) {
				return new JwtAuthentication(user, null, new ArrayList<>(),p);
			}
			return null;
		}
		return null;
	}

	private Properties getProperties(DecodedJWT verify) {
		Properties p = new Properties();
		Map<String, Claim> claims = verify.getClaims();
		for (String key : claims.keySet()) {
			Claim claim = claims.get(key);
			String value = claim.asString()==null?"":claim.asString();
			p.put(key, value);
		}
		return p;
	}
}