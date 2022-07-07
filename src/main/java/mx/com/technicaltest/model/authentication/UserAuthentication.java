package mx.com.technicaltest.model.authentication;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * The main object that represents an complete authentication done by the user,
 * this implementation uses the builder pattern for some optional fields.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@ToString
@Slf4j
@EqualsAndHashCode
@JsonDeserialize(builder = UserAuthentication.UserAuthenticationBuilder.class)
public class UserAuthentication implements Authentication {

	private static final long serialVersionUID = -7738048062797139332L;

	/**
	 * The username of the user
	 */
	private final String username;

	/**
	 * The user's password
	 */
	private final String password;

	/**
	 * The user's roles
	 */
	private final Set<GrantedAuthority> roles;

	/**
	 * The token type retrived
	 */
	private final String tokenType;

	/**
	 * The user's access token
	 */
	private final String accessToken;

	/**
	 * The time to live of the access token
	 */
	private final String expiresIn;

	/**
	 * The user's refresh token for get a new access token
	 */
	private final String refreshToken;

	/**
	 * This class uses the builder pattern so this constructor is marked as private
	 * and the param is an inner class that will be a helper class for build the
	 * entire object.
	 * 
	 * @param builder the helper inner class.
	 */
	private UserAuthentication(final UserAuthenticationBuilder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.roles = builder.roles;
		this.tokenType = builder.tokenType;
		this.accessToken = builder.accessToken;
		this.expiresIn = builder.expiresIn;
		this.refreshToken = builder.refreshToken;
	}

	@JsonPOJOBuilder(withPrefix = EMPTY, buildMethodName = "build")
	public static class UserAuthenticationBuilder {

		/**
		 * The username of the user
		 */
		private String username;

		/**
		 * The user's password
		 */
		private String password;

		/**
		 * The user's roles
		 */
		private Set<GrantedAuthority> roles;

		/**
		 * The token type retrived
		 */
		private String tokenType;

		/**
		 * The time to live of the access token
		 */
		private String expiresIn;

		/**
		 * The user's access token
		 */
		private String accessToken;

		/**
		 * The user's refresh token for get a new access token
		 */
		private String refreshToken;

		/**
		 * Set the username in the builder object and return the builder's reference
		 * 
		 * @param username the user's username
		 * @return the reference of this actual object
		 */
		public UserAuthenticationBuilder username(final String username) {
			this.username = username;
			return this;
		}

		/**
		 * Set the password in the builder object and return the builder's reference
		 * 
		 * @param password the user's password
		 * @return the reference of this actual object
		 */
		public UserAuthenticationBuilder password(final String password) {
			this.password = password;
			return this;
		}

		/**
		 * Set the roles in the builder object and return the builder's reference
		 * 
		 * @param roles the user's roles
		 * @return the reference of this actual object
		 */
		public UserAuthenticationBuilder roles(final List<String> roles) {
			this.roles = mapToGrantedAuthorities(roles);
			return this;
		}

		/**
		 * Set the token type in the builder object and return the builder's reference
		 * 
		 * @param tokenType the user's token type
		 * @return the reference of this actual object
		 */
		@JsonProperty("token_type")
		public UserAuthenticationBuilder tokenType(final String tokenType) {
			this.tokenType = tokenType;
			return this;
		}

		/**
		 * Set the expiration time of the access token in the builder object and return
		 * the builder's reference
		 * 
		 * @param expiresIn the user's expiresIn
		 * @return the reference of this actual object
		 */
		@JsonProperty("expires_in")
		public UserAuthenticationBuilder expiresIn(final String expiresIn) {
			this.expiresIn = expiresIn;
			return this;
		}

		/**
		 * Set access token in the builder object and return the builder's reference
		 * 
		 * @param accessToken the user's accessToken
		 * @return the reference of this actual object
		 */
		@JsonProperty("access_token")
		public UserAuthenticationBuilder accessToken(final String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		/**
		 * Set refresh token in the builder object and return the builder's reference
		 * 
		 * @param refreshToken the user's refreshToken
		 * @return the reference of this actual object
		 */
		@JsonProperty("refresh_token")
		public UserAuthenticationBuilder refreshToken(final String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}

		/**
		 * Build the Object using the outer class.
		 * 
		 * @return a new Authentication Object
		 */
		public Authentication build() {
			return new UserAuthentication(this);
		}

		/**
		 * Map the string list into a new set with GrantedAuthority objects
		 * 
		 * @param roles
		 * @return
		 */
		private static Set<GrantedAuthority> mapToGrantedAuthorities(final List<String> roles) {
			return roles.stream().map((String role) -> {
				return new GrantedAuthority() {
					private static final long serialVersionUID = 4499059770202725476L;

					public String getAuthority() {
						return role;
					}
				};
			}).collect(toSet());
		}

	}

	@Override
	public String getName() {
		return this.username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public Object getCredentials() {
		return this.password;
	}

	@Override
	public Object getDetails() {
		log.error("The call to 'getDetails' method has no implementation.");
		throw new UnsupportedOperationException("The current method has no implementation.");
	}

	@Override
	public Object getPrincipal() {
		return this.username;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public String getTokenType() {
		return tokenType;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	}

}