package com.example.auth0.security;

import java.util.Collection;
import java.util.Properties;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthentication extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -4506786578482555492L;
	private Properties properties;

	public JwtAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,
			Properties properties) {
		super(principal, credentials, authorities);
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
