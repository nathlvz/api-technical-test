package mx.com.technicaltest.model.authentication;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The model that represent the user credentials for making an authentication.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserCredentials {

	/**
	 * The username
	 */
	private String username;

	/**
	 * The password
	 */
	private String password;

	/**
	 * Private contructor for prevent instanciation.
	 * 
	 * @param username
	 * @param password
	 */
	private UserCredentials(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Convenient method that build a new instance of this class.
	 * 
	 * @param username The username
	 * @param password The password
	 * @return
	 */
	public static UserCredentials of(final String username, final String password) {
		return new UserCredentials(username, password);
	}

}
