package mx.com.technicaltest.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.technicaltest.model.movement.Account;
import mx.com.technicaltest.model.movement.Concept;

/**
 * The movement document.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "movements")
public class Movement {

	/**
	 * The identifier for this movement
	 */
	@Id
	private String id;

	/**
	 * The movement's amount
	 */
	private BigDecimal amount;

	/**
	 * The movement's balance
	 */
	private BigDecimal balance;

	/**
	 * The movement's custom date
	 */
	private String customDate;

	/**
	 * The movement's custom description
	 */
	private String customDescription;

	/**
	 * The movement's date
	 */
	private String date;

	/**
	 * The movement's date created
	 */
	private String dateCreated;

	/**
	 * The movement's flag for deletion
	 */
	private boolean deleted;

	/**
	 * The movement's description
	 */
	private String description;

	/**
	 * The movement's flag for duplicate
	 */
	private boolean duplicated;

	/**
	 * The movement's flag indicating that has concepts
	 */
	private boolean hasConcepts;

	/**
	 * The movement's that indicates the resume flag
	 */
	private boolean inResume;

	/**
	 * The movement's last updated
	 */
	private String lastUpdated;

	/**
	 * The movement's type
	 */
	private String type;

	/**
	 * The movement's account
	 */
	private Account account;

	/**
	 * The movement's concept list
	 */
	private List<Concept> concepts;

}
