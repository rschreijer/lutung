package com.microtripit.mandrillapp.lutung.view;

import java.util.Date;

/**
 * <p>Information for a rejection blacklist entry.</p>
 * @author rschreijer
 * @since Mar 18, 2013
 */
public class MandrillRejectsEntry {
	private String email, reason;
	private Date created_at, expires_at;
	private Boolean expired;
	// TODO: Sender
	
	/**
	 * @return The email that is blocked.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return The type of event (hard-bounce, 
	 * soft-bounce, spam, unsub) that caused 
	 * this rejection.
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @return When this email was added to the 
	 * blacklist, UTC.
	 */
	public Date getCreatedAt() {
		return created_at;
	}
	/**
	 * @return When this blacklist entry will 
	 * expire (this may be in the past), UTC.
	 */
	public Date getExpiresAt() {
		return expires_at;
	}
	/**
	 * @return Whether the blacklist entry has expired.
	 */
	public Boolean isExpired() {
		return expired;
	}
}
