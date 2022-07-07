package mx.com.technicaltest.configuration;

import static org.springframework.web.reactive.function.client.WebClient.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import mx.com.technicaltest.configuration.properties.FinerioServices;

/**
 * Configuration class that contains all the clients needed.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Configuration
@RequiredArgsConstructor
public class ConfigurationForClients {

	/**
	 * The others Finerio's path for call other services
	 */
	private final FinerioServices finerioServices;

	/**
	 * WebClient configured with the main path for call all the finerio's services.
	 * 
	 * @return a new web client set and ready for making http calls to Finerio.
	 */
	@Bean
	public WebClient finerioWebClient() {
		return create(this.finerioServices.getApi());
	}

}
