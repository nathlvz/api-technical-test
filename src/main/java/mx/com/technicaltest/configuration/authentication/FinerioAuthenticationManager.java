package mx.com.technicaltest.configuration.authentication;

import static mx.com.technicaltest.model.authentication.UserCredentials.of;
import static mx.com.technicaltest.utils.Headers.CONTENT_TYPE_HEADER;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static reactor.core.publisher.Mono.error;
import static reactor.core.publisher.Mono.just;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.technicaltest.configuration.properties.FinerioServices;
import mx.com.technicaltest.model.authentication.UserAuthentication;
import mx.com.technicaltest.model.authentication.UserCredentials;
import reactor.core.publisher.Mono;

/**
 * The Strategy for authenticate the users using Basic Authentication with the
 * Finerio's service for login.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FinerioAuthenticationManager implements ReactiveAuthenticationManager {

	/**
	 * The WebClient with the host set for make any HTTP call.
	 */
	private final WebClient finerioWebClient;

	/**
	 * The others Finerio's path for call other services.
	 */
	private final FinerioServices finerioServices;

	/**
	 * Method that perform the authentication with Finerio using the HTTP Basic
	 * Strategy with a Bearer token.
	 */
	// @formatter:off
	@Override
	public Mono<Authentication> authenticate(final Authentication authentication) {
		log.info("Executing the authentication with Finerio.");
		final String username = (String) authentication.getPrincipal();
		final String password = (String) authentication.getCredentials();
		return this.finerioWebClient.post()
				.uri(this.finerioServices.getLogin())
				.body(just(of(username, password)), UserCredentials.class)
				.accept(APPLICATION_JSON)
				.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_VALUE)
				.exchangeToMono((ClientResponse clientResponse) -> {
					return clientResponse.bodyToMono(UserAuthentication.class)
							.cast(Authentication.class);
				})
				.switchIfEmpty(error(new AuthenticationCredentialsNotFoundException("Incorrect credentials.")))
				.doOnSuccess((Authentication auth) -> {
					log.info("The authentication was successful, ready for work!");
				})
				.doOnError((Throwable error) -> {
					log.error("There's a problem with the authentication, the cause is: {}", error.getMessage());
				});
	}
	// @formatter:on

}
