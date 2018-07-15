package sb.jwt.playwithJWT.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
		return http.authorizeExchange().pathMatchers("/users/{username}")
				.access((authentication, context) -> authentication
						.map(auth -> auth.getName().equals(context.getVariables().get("username")))
						.map(AuthorizationDecision::new))
				.anyExchange().authenticated()
				.and().authenticationManager(reactiveAuthenticationManager()).httpBasic()
				.and().build();
	}

	@Bean
	ReactiveAuthenticationManager reactiveAuthenticationManager() {
		return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsRepository());
	}

	@Bean
	public MapReactiveUserDetailsService userDetailsRepository() {
		//TODO: change this passcode default
		User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
		UserDetails shaib = userBuilder.username("shai").roles("USER").password("password").build();
		UserDetails admin = userBuilder.username("admin").password("admin").roles("USER", "ADMIN").build();
		return new MapReactiveUserDetailsService(shaib, admin);
	}
}
