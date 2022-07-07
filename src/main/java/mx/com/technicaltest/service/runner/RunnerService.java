package mx.com.technicaltest.service.runner;

import org.springframework.security.core.Authentication;

import reactor.core.publisher.Mono;

/**
 * Contract or service that defines tha main operations, in this case only one:
 * run the test!
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
public interface RunnerService {

	/**
	 * Run the test with the case described in the controller!
	 * 
	 * @param auth the authentication object managed by Spring
	 * @return a new Mono with a void value.
	 */
	Mono<Void> runTest(Mono<Authentication> auth);
}
