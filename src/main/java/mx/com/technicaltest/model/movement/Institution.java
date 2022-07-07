package mx.com.technicaltest.model.movement;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The model that represents an institution.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Institution {

	/**
	 * The institution's identifier
	 */
	private String id;

	/**
	 * The institution's code
	 */
	private String code;

	/**
	 * The institution's type
	 */
	private String institutionType;

	/**
	 * The institution's name
	 */
	private String name;

	/**
	 * The institution's status
	 */
	private String status;

}
