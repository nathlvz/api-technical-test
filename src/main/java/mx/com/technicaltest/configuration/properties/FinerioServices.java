package mx.com.technicaltest.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The api or host and all the services needed.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "finerio-services")
public class FinerioServices {

	/**
	 * The api or host
	 */
	private String api;

	/**
	 * The path of the login service
	 */
	private String login;

	/**
	 * The path of the 'me' service
	 */
	private String me;

	/**
	 * The path of movements service
	 */
	private String movements;

}
