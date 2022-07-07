package mx.com.technicaltest.model.movement;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The model that represents a concept
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Concept {

	/**
	 * The concept's identifier
	 */
	private String id;

	/**
	 * The concept's amount
	 */
	private BigDecimal amount;

	/**
	 * The concept's description
	 */
	private String description;

	/**
	 * The concept's type
	 */
	private String type;

	/**
	 * The concept's movement
	 */
	private String movement;

}
