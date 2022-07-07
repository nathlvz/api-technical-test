package mx.com.technicaltest.model.movement;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The movement's query params.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MovementsParams {

	/**
	 * Flag for deep
	 */
	private boolean deep;

	/**
	 * The index for the pagination
	 */
	private int offset;

	/**
	 * Total of records
	 */
	private int max;

	/**
	 * Flag for include charges
	 */
	private boolean includeCharges;

	/**
	 * Flag for include deposits
	 */
	private boolean includeDeposits;

	/**
	 * Flag for include duplicates
	 */
	private boolean includeDuplicates;

}
