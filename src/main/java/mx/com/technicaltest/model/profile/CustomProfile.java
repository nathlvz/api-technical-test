package mx.com.technicaltest.model.profile;

import java.time.ZonedDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The model that represents a profile.
 * 
 * @author Benjamin Natanael Ocotzi Alvarez - beniz
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CustomProfile {

	/**
	 * The profile's identifier
	 */
	private String id;

	/**
	 * The profile's flag for indicates that this account is expired
	 */
	private boolean accountExpired;

	/**
	 * The profile's flag for indicates that this account is locked
	 */
	private boolean accountLocked;

	/**
	 * The customer id
	 */
	private long customerId;

	/**
	 * The profile's date created
	 */
	private ZonedDateTime dateCreated;

	/**
	 * The profile's email
	 */
	private String email;

	/**
	 * The profile's flag for indicates that this account is enabled
	 */
	private boolean enabled;

	/**
	 * The profile's last updated
	 */
	private ZonedDateTime lastUpdated;

	/**
	 * The profile's name
	 */
	private String name;

	/**
	 * The profile's flag for indicates that this account has the notifications
	 * enabled
	 */
	private boolean notificationsEnabled;

	/**
	 * The profile's flag for indicates that this account has a password already
	 * expired
	 */
	private boolean passwordExpired;

	/**
	 * signupCredentialEmailSent
	 */
	private boolean signupCredentialEmailSent;

	/**
	 * signupCredentialPushSent
	 */
	private boolean signupCredentialPushSent;

	/**
	 * signupEmailSent
	 */
	private boolean signupEmailSent;

	/**
	 * signupFrom
	 */
	private String signupFrom;

	/**
	 * The profile's flag for indicates that this account has accepted the new
	 * terms.
	 */
	private boolean termsAndConditionsAccepted;

	/**
	 * The profile's type
	 */
	private String type;

	/**
	 * The profile's finerio code
	 */
	private String finerioCode;

	/**
	 * The profile's flag for indicates that this account has the new terms
	 */
	private boolean hasNewTerms;

}