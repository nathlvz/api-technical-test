package mx.com.technicaltest.utils;

import lombok.experimental.UtilityClass;

/**
 * Utility class that contains only the constants for headers.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@UtilityClass
public class Headers {

	/**
	 * Constant indicating the Authorization header.
	 */
	public static final String AUTHORIZATION_HEADER = "Authorization";

	/**
	 * Constant indicating the Content-Type header.
	 */
	public static final String CONTENT_TYPE_HEADER = "Content-Type";

	/**
	 * private constant for add a new empty space.
	 */
	private static final String SPACE = " ";

	/**
	 * Concat the token type with the token, this is useful with basic auth.
	 * 
	 * @param tokenType token type
	 * @param token     the token value
	 * @return the result of concat these values.
	 */
	public static String authHeaderValue(final String tokenType, final String token) {
		return tokenType.concat(SPACE).concat(token);
	}

}
