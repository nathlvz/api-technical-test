package mx.com.technicaltest.controller.runner;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Mono;

/**
 * The main controller for run the test!
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
public interface RunnerController {

	/**
	 * Call the login(indirect), me and movements, so if the basic auth fails then a
	 * new message in console will be displayed, otherwise the first 10 records will
	 * be retrived and saved in the database, after other execution the others 10
	 * records will be saved!
	 * 
	 * @param auth the authentication object managed by Spring
	 * @return an empty response entity (because all is shown in console :D)
	 */
	@GetMapping("/runTest")
	ResponseEntity<Mono<Void>> runTest(Mono<Authentication> auth);

}
