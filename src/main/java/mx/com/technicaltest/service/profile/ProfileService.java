package mx.com.technicaltest.service.profile;

import org.springframework.security.core.Authentication;

import mx.com.technicaltest.model.profile.CustomProfile;
import reactor.core.publisher.Mono;

/**
 * Contract for get the user profile.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 */
public interface ProfileService {

	/**
	 * Get the user profile.
	 * 
	 * @param authentication the authentication object managed by Spring
	 * @return a new mono with the user profile
	 */
	Mono<CustomProfile> getProfile(Mono<Authentication> authentication);

}
