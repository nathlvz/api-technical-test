package mx.com.technicaltest.controller.runner.implementation;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mx.com.technicaltest.controller.runner.RunnerController;
import mx.com.technicaltest.service.runner.RunnerService;
import reactor.core.publisher.Mono;

/**
 * Implementation of
 * {@code mx.com.technicaltest.controller.runner.RunnerController}
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class RunnerControllerImplementation implements RunnerController {

	/**
	 * Composite service that orchestrates all the flow.
	 */
	private final RunnerService runnerService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Mono<Void>> runTest(final Mono<Authentication> auth) {
		return ok(this.runnerService.runTest(auth));
	}

}
