package mx.com.technicaltest.configuration;

import static org.springframework.http.HttpMethod.GET;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;

/**
 * The application's security config, url protect, role verification and HTTP
 * Basic enable using finerio as the authentication provider.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 */
@Configuration
public class ConfigurationForSecurity {

	/**
	 * Default role for authentication using login service.
	 */
	@Value("${security.roles.no-role}")
	private String noRole;

	/**
	 * Main Spring's filter chain that configure url protect, role verification and
	 * http basic.
	 * 
	 * @param httpSecurity                 the chain builder.
	 * @param finerioEntryPoint            the entry point for http basic auth
	 * @param finerioAuthenticationManager the strategy for authenticate users with
	 *                                     the services.
	 * @return a new filter chain.
	 */
	// @formatter:off
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity httpSecurity,
			@Qualifier("finerioEntryPoint") final ServerAuthenticationEntryPoint finerioEntryPoint,
			@Qualifier("finerioAuthenticationManager") final ReactiveAuthenticationManager finerioAuthenticationManager) {
		return httpSecurity
				.authorizeExchange()
				.pathMatchers(GET, "/runTest")
				.hasRole(this.noRole)
				.and()
				.httpBasic()
				.authenticationManager(finerioAuthenticationManager)
				.authenticationEntryPoint(finerioEntryPoint)
				.and()
				.formLogin()
				.disable()
				.csrf()
				.disable()
				.build();
	}
	// @formatter:on

}
