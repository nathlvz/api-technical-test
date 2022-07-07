package mx.com.technicaltest.utils;

import lombok.experimental.UtilityClass;

/**
 * Convenient utility class for add other detailed class related to their
 * contexts.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@UtilityClass
public class Params {

	/**
	 * Utility class for declarate all the query params for the movements service.
	 * 
	 * @author Benjamin Natanael Ocotzi Alvarez - beniz
	 *
	 */
	@UtilityClass
	public static class Movements {

		/**
		 * Query param for deep
		 */
		public static final String DEEP_PARAM = "deep";

		/**
		 * Query param for offset
		 */
		public static final String OFFSET_PARAM = "offset";

		/**
		 * Query param for max
		 */
		public static final String MAX_PARAM = "max";

		/**
		 * Query param for include charges
		 */
		public static final String INCLUDE_CHARGES_PARAM = "includeCharges";

		/**
		 * Query param for include deposits
		 */
		public static final String INCLUDE_DEPOSITS_PARAM = "includeDeposits";

		/**
		 * Query param for include duplicates
		 */
		public static final String INCLUDE_DUPLICATES_PARAM = "includeDuplicates";

	}
}
