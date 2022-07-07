package mx.com.technicaltest.service.movement.implementation;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newWorkStealingPool;
import static mx.com.technicaltest.utils.Headers.AUTHORIZATION_HEADER;
import static mx.com.technicaltest.utils.Headers.authHeaderValue;
import static mx.com.technicaltest.utils.Params.Movements.DEEP_PARAM;
import static mx.com.technicaltest.utils.Params.Movements.INCLUDE_CHARGES_PARAM;
import static mx.com.technicaltest.utils.Params.Movements.INCLUDE_DEPOSITS_PARAM;
import static mx.com.technicaltest.utils.Params.Movements.INCLUDE_DUPLICATES_PARAM;
import static mx.com.technicaltest.utils.Params.Movements.MAX_PARAM;
import static mx.com.technicaltest.utils.Params.Movements.OFFSET_PARAM;
import static reactor.core.publisher.Mono.just;
import static reactor.core.publisher.Mono.zip;
import static reactor.core.scheduler.Schedulers.fromExecutor;

import java.net.URI;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.technicaltest.configuration.properties.FinerioServices;
import mx.com.technicaltest.entity.Movement;
import mx.com.technicaltest.mappers.MovementMapper;
import mx.com.technicaltest.model.authentication.UserAuthentication;
import mx.com.technicaltest.model.movement.MovementDataWrapper;
import mx.com.technicaltest.model.movement.MovementInfo;
import mx.com.technicaltest.model.movement.MovementsParams;
import mx.com.technicaltest.model.profile.CustomProfile;
import mx.com.technicaltest.repository.movement.MovementRepository;
import mx.com.technicaltest.service.movement.MovementService;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

/**
 * Implementation of
 * {@code mx.com.technicaltest.service.movement.MovementService}
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MovementServiceImplementation implements MovementService {

	/**
	 * WebClient configured with the main path for call all the finerio's services.
	 */
	private final WebClient finerioWebClient;

	/**
	 * The others Finerio's path for call other services.
	 */
	private final FinerioServices finerioServices;

	/**
	 * Repository for make operations with movements.
	 */
	private final MovementRepository movementRepository;

	/**
	 * {@inheritDoc}
	 */
	// @formatter:off
	@Override
	public Mono<MovementDataWrapper> getMovements(final Mono<Authentication> authentication,
			final Mono<CustomProfile> profile, final Mono<MovementsParams> movementsParams) {
		log.info("Getting the profile information.");
		return zip(authentication, profile, movementsParams)
				.flatMap(this::getMovements)
				.doOnSuccess(this::notifySuccessfulResult);

	}
	// @formatter:on

	/**
	 * Get all the movements using basic http auth.
	 * 
	 * @param tuple3 the result of zip 3 monos with the Authentication,
	 *               CustomProfile and MovementsParams.
	 * @return a new mono with the data of movements.
	 */
	// @formatter:off
	private Mono<MovementDataWrapper> getMovements(final Tuple3<Authentication, CustomProfile, MovementsParams> tuple3){
		final UserAuthentication userAuth = (UserAuthentication) tuple3.getT1();
		final CustomProfile customProfile = tuple3.getT2();
		final MovementsParams movementParams = tuple3.getT3();
		return this.finerioWebClient.get()
			.uri(this.buildUriWithParams(movementParams, customProfile))
			.header(AUTHORIZATION_HEADER,authHeaderValue(userAuth.getTokenType(), 
					userAuth.getAccessToken()))
			.exchangeToMono(this::responseHandlerMovementDataWrapper);
	}
	// @formatter:on

	/**
	 * Return a new function with the composite uri.
	 * 
	 * @param movementParams the params for movements service
	 * @param customProfile  the profile object
	 * @return function to build a new uri for movement service.
	 */
	// @formatter:off
	private Function<UriBuilder, URI> buildUriWithParams(final MovementsParams movementParams,
			final CustomProfile customProfile) {
		return (final UriBuilder builder) -> {
			return builder.path(this.finerioServices.getMovements())
					.queryParam(DEEP_PARAM, movementParams.isDeep())
					.queryParam(OFFSET_PARAM, movementParams.getOffset())
					.queryParam(MAX_PARAM, movementParams.getMax())
					.queryParam(INCLUDE_CHARGES_PARAM, movementParams.isIncludeCharges())
					.queryParam(INCLUDE_DEPOSITS_PARAM, movementParams.isIncludeDeposits())
					.queryParam(INCLUDE_DUPLICATES_PARAM, movementParams.isIncludeDuplicates())
					.build(customProfile.getId());
		};
	}
	// @formatter:on

	/**
	 * Handler for transform the reponse's body to a new mono of type
	 * {@code mx.com.technicaltest.model.movement.MovementDataWrapper}
	 * 
	 * @param clientResponse the response
	 * @return new mono with the data of movements.
	 */
	private Mono<MovementDataWrapper> responseHandlerMovementDataWrapper(final ClientResponse clientResponse) {
		return clientResponse.bodyToMono(MovementDataWrapper.class);
	}

	/**
	 * Display the movement data in console.
	 * 
	 * @param movementDataWrapper the data of movements.
	 */
	private void notifySuccessfulResult(final MovementDataWrapper movementDataWrapper) {
		log.info("The information can be retrived, total of movements: {}", movementDataWrapper.getSize());
	}

	/**
	 * {@inheritDoc}
	 */
	// @formatter:off
	@Override
	@Transactional
	public Mono<Movement> saveMovement(MovementInfo movementInfo) {
		return just(movementInfo)
				.publishOn(fromExecutor(newWorkStealingPool(getRuntime().availableProcessors())))
				.map(MovementMapper.INSTANCE::mapToMovement)
				.flatMap(this.movementRepository::save)
				.doOnSuccess((final Movement movement) -> {
					log.info("The movement: {} was saved!", movement);
				});
	}
	// @formatter:on

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Mono<Long> countMovements() {
		return this.movementRepository.count();
	}

}
