package mx.com.technicaltest.service.runner.implementation;

import static reactor.core.publisher.Mono.just;

import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.technicaltest.model.movement.MovementDataWrapper;
import mx.com.technicaltest.model.movement.MovementsParams;
import mx.com.technicaltest.model.profile.CustomProfile;
import mx.com.technicaltest.service.movement.MovementService;
import mx.com.technicaltest.service.profile.ProfileService;
import mx.com.technicaltest.service.runner.RunnerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of {@code mx.com.technicaltest.service.runner.RunnerService}
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RunnerServiceImplementation implements RunnerService {

	/**
	 * The service for get the profile
	 */
	private final ProfileService profileService;

	/**
	 * The service for get the movements
	 */
	private final MovementService movementService;

	/**
	 * Flag indicating that this is the first execution
	 */
	private final long FIRST_EXECUTION = 0;

	/**
	 * Flag indicating that this is the second execution
	 */
	private final long SECOND_EXECUTION = 10;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<Void> runTest(final Mono<Authentication> auth) {
		final MovementsParams movementsParams = this.getDefaultMovementsParams();
		return this.getMovementData(auth, movementsParams).flatMap((final MovementDataWrapper movementDataWrapper) -> {
			return this.saveMovements(auth, movementDataWrapper, movementsParams);
		}).then();
	}

	/**
	 * Creates a new object with all the movement's query params.
	 * 
	 * @return a new Object with all the query params needed fro the movement
	 *         service.
	 */
	private MovementsParams getDefaultMovementsParams() {
		final MovementsParams movementsParams = new MovementsParams();
		movementsParams.setDeep(true);
		movementsParams.setOffset(0);
		movementsParams.setMax(10);
		movementsParams.setIncludeCharges(true);
		movementsParams.setIncludeDeposits(true);
		movementsParams.setIncludeDuplicates(true);
		return movementsParams;
	}

	/**
	 * Get the movement data.
	 * 
	 * @param auth            the Authentication Object managed by Spring
	 * @param movementsParams the movement's service query params
	 * @return a Mono with the MovementDataWrapper
	 */
	private Mono<MovementDataWrapper> getMovementData(final Mono<Authentication> auth,
			final MovementsParams movementsParams) {
		return this.profileService.getProfile(auth).doOnSuccess(this::notifyProfileInformation)
				.flatMap(this.retrieveMovements(auth, movementsParams));
	}

	/**
	 * Creates a function with the movements retrived.
	 * 
	 * @param auth            the Authentication Object managed by Spring
	 * @param movementsParams the movement's service query params
	 * @return a new function with the movements
	 */
	private Function<CustomProfile, Mono<MovementDataWrapper>> retrieveMovements(final Mono<Authentication> auth,
			final MovementsParams movementsParams) {
		return (CustomProfile profile) -> {
			return this.movementService.getMovements(auth, just(profile), just(movementsParams));
		};
	}

	/**
	 * Display the profile information in console
	 * 
	 * @param profile the object with the profile information
	 */
	private void notifyProfileInformation(final CustomProfile profile) {
		log.info("Processing information with the profile id: {}", profile.getId());
	}

	/**
	 * Save the records retrive from the first and the second execution!
	 * 
	 * @param auth                the Authentication Object managed by Spring
	 * @param movementDataWrapper the first execution result
	 * @param movementsParams     the movement's service query params
	 * @return an empty mono
	 */
	private Mono<Void> saveMovements(final Mono<Authentication> auth, final MovementDataWrapper movementDataWrapper,
			final MovementsParams movementsParams) {
		return Mono.zip(just(movementDataWrapper), this.movementService.countMovements()).flatMap((tuple2) -> {
			final MovementDataWrapper data = tuple2.getT1();
			final Long count = tuple2.getT2();
			if (count == FIRST_EXECUTION) {
				log.info("1st Execution!, Getting the first 10 movements.");
				return Flux.fromIterable(data.getData()).flatMap(this.movementService::saveMovement).then();
			} else if (count == SECOND_EXECUTION) {
				log.info("2nd Execution!, Getting the others 10 remaining movements.");
				movementsParams.setOffset(10);
				movementsParams.setMax(10);
				return this.getMovementData(auth, movementsParams).flatMapMany((final MovementDataWrapper wrapper) -> {
					return Flux.fromIterable(wrapper.getData());
				}).flatMap(this.movementService::saveMovement).then();
			} else {
				return Mono.empty();
			}
		});
	}
}
