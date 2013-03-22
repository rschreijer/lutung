package com.microtripit.mandrillapp.lutung.view;

/**
 * <p>Recipient information.</p>
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillInboundRecipient {
	private String email, pattern, url;

	/**
	 * @return The email address of the matching recipient.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return The mailbox route pattern that the recipient matched.
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @return The webhook URL that the message was posted to.
	 */
	public String getUrl() {
		return url;
	}

}
