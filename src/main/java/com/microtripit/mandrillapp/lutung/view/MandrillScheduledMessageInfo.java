/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

import java.util.Date;

/**
 * <p>Info on a scheduled email.</p>
 * @author rschreijer
 *
 */
public class MandrillScheduledMessageInfo {
	private String _id, from_email, to, subject;
	private Date created_at, send_at;
	
	/**
	 * @return The scheduled message id.
	 */
	public String getId() {
		return _id;
	}
	/**
	 * @return The email's sender address.
	 */
	public String getFromEmail() {
		return from_email;
	}
	/**
	 * @return The email's recipient.
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @return The email's subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @return The time when the message was created.
	 */
	public Date getCreated_at() {
		return created_at;
	}
	/**
	 * @return The time when the message will be sent.
	 */
	public Date getSend_at() {
		return send_at;
	}

}
