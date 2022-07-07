package mx.com.technicaltest.configuration.authentication;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static reactor.core.publisher.Mono.*;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Component that will be called if the Basic Authentication isn't successful
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 */
@Slf4j
@Component
public class FinerioEntryPoint implements ServerAuthenticationEntryPoint {

	/**
	 * Message indicating that the authentication was unsuccessful.
	 */
	private static final String MESSAGE = "The authentication performed was unsuccessful.";

	/**
	 * Method that writes a response in case of error!
	 */
	@Override
	public Mono<Void> commence(final ServerWebExchange exchange, final AuthenticationException exception) {
		return fromRunnable(() -> {
			final ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(FORBIDDEN);
			log.error(MESSAGE);
		});
	}

}
