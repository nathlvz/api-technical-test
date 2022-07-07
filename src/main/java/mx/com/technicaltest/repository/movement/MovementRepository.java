package mx.com.technicaltest.repository.movement;

import org.springframework.stereotype.Repository;

import mx.com.technicaltest.entity.Movement;
import reactor.core.publisher.Mono;

/**
 * Repository for operations with movements
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Repository
public interface MovementRepository extends org.springframework.data.repository.Repository<Movement, String> {

	/**
	 * Save the movement in a database.
	 * 
	 * @param movement the movement information.
	 * @return the movement saved.
	 */
	Mono<Movement> save(Movement movement);

	/**
	 * Counts all the movements stored in the database.
	 * 
	 * @return the total of movements.
	 */
	Mono<Long> count();

}
