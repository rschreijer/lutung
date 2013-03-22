/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

/**
 * <p>The sending results for a single recipient.</p>
 * @author rschreijer
 * @since Mar 16, 2013
 */
public class MandrillMessageStatus {
	private String email, status, _id;

	/**
	 * @return The email address of the recipient.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return The sending status of the recipient &ndash; 
	 * either "sent", "queued", "rejected", or "invalid".
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return The message's unique id.
	 */
	public String getId() {
		return _id;
	}
	
}
