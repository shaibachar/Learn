package sb.jwt.playwithJWT.configuration;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Configuration
public class WebConfiguration {


	Mono<ServerResponse> messageHandler(ServerRequest serverRequest) {
		Mono<String> principalMap = serverRequest.principal().map(p -> "Hello, " + p.getName() + "!");
		return ServerResponse.ok().body(principalMap, String.class);
	}

	Mono<ServerResponse> userNameHandler(ServerRequest serverRequest) {
		Mono<UserDetails> details = serverRequest.principal().map(p -> UserDetails.class.cast(Authentication.class.cast(p).getPrincipal()));
		return ServerResponse.ok().body(details, UserDetails.class);

	}
	
	@Bean
	RouterFunction<?> routes() {
		return route(GET("/sayHi"), this::messageHandler).andRoute(GET("/users/{username}"), this::userNameHandler);
	}
}
