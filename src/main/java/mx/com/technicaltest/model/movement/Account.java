package mx.com.technicaltest.model.movement;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The account information
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Account {

	/**
	 * The account's indentifier
	 */
	private String id;

	/**
	 * The account's available balance
	 */
	private BigDecimal availableBalance;

	/**
	 * The account's 
	 */
	private BigDecimal balance;

	/**
	 * The account's date created
	 */
	private String dateCreated;

	/**
	 * The account's flag for deletion
	 */
	private boolean deleted;

	/**
	 * The account's last updated
	 */
	private String lastUpdated;

	/**
	 * The account's name
	 */
	private String name;

	/**
	 * The account's number
	 */
	private String number;

	/**
	 * The account's type
	 */
	private String type;

	/**
	 * The account's user
	 */
	private User user;
	
	/**
	 * The account's institution
	 */
	private Institution institution;

}
