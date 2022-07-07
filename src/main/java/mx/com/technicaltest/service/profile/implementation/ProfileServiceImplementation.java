package mx.com.technicaltest.service.profile.implementation;

import static mx.com.technicaltest.utils.Headers.AUTHORIZATION_HEADER;
import static mx.com.technicaltest.utils.Headers.authHeaderValue;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.technicaltest.configuration.properties.FinerioServices;
import mx.com.technicaltest.model.authentication.UserAuthentication;
import mx.com.technicaltest.model.profile.CustomProfile;
import mx.com.technicaltest.service.profile.ProfileService;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImplementation implements ProfileService {

	private final WebClient finerioWebClient;

	private final FinerioServices finerioServices;

	// @formatter:off
	@Override
	public Mono<CustomProfile> getProfile(final Mono<Authentication> authentication) {
		log.info("Getting the profile information.");
		return authentication.flatMap(this::getProfile);
	}
	// @formatter:on

	// @formatter:off
	private Mono<CustomProfile> getProfile(final Authentication auth) {
		final UserAuthentication userAuth = (UserAuthentication) auth;
		return this.finerioWebClient.get()
			.uri(this.finerioServices.getMe())
			.header(AUTHORIZATION_HEADER, authHeaderValue(userAuth.getTokenType(), 
				userAuth.getAccessToken()))
			.exchangeToMono(this::responseHandlerCustomProfile);
	}
	// @formatter:on

	private Mono<CustomProfile> responseHandlerCustomProfile(final ClientResponse clientResponse) {
		return clientResponse.bodyToMono(CustomProfile.class);
	}

}
