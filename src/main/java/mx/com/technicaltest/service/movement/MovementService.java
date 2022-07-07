package mx.com.technicaltest.service.movement;

import org.springframework.security.core.Authentication;

import mx.com.technicaltest.entity.Movement;
import mx.com.technicaltest.model.movement.MovementDataWrapper;
import mx.com.technicaltest.model.movement.MovementInfo;
import mx.com.technicaltest.model.movement.MovementsParams;
import mx.com.technicaltest.model.profile.CustomProfile;
import reactor.core.publisher.Mono;

/**
 * Contract for make operations with the movements.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
public interface MovementService {

	/**
	 * Get the movements
	 * 
	 * @param userAuth        the authentication object managed by Spring
	 * @param profile         the profile object
	 * @param movementsParams the query params for filter the movements
	 * @return the data of movements
	 */
	Mono<MovementDataWrapper> getMovements(Mono<Authentication> userAuth, Mono<CustomProfile> profile,
			Mono<MovementsParams> movementsParams);

	/**
	 * Save or Update the movement in the database
	 * 
	 * @param movementInfo the movement information
	 * @return a mono with the movement stored.
	 */
	Mono<Movement> saveMovement(MovementInfo movementInfo);

	/**
	 * Return the total of the movements.
	 * 
	 * @return total of movements
	 */
	Mono<Long> countMovements();

}
