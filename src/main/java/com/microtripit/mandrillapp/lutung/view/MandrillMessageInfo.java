/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

import java.util.List;

/**
 * <p>Basic information for a message.</p>
 * @author rschreijer
 * @since Mar 16, 2013
 */
public class MandrillMessageInfo {
	private Integer ts, opens, clicks;
	private String _id, sender, subject, email, state, diag, bounce_description;
	private List<String> tags;
	// TODO: private Object metadata;
	
	/**
	 * @return The Unix timestamp from when this message was sent.
	 */
	public Integer getTs() {
		return ts;
	}
	/**
	 * @return How many times has this message been opened.
	 */
	public Integer getOpens() {
		return opens;
	}
	/**
	 * @return How many times has a link been clicked in this message.
	 */
	public Integer getClicks() {
		return clicks;
	}
	/**
	 * @return The message's unique id.
	 */
	public String getId() {
		return _id;
	}
	/**
	 * @return The email address of the sender.
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * @return The message's subject link.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @return The recipient email address.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return The sending status of this message: sent, bounced, rejected.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return List of tags on this message.
	 */
	public List<String> getTags() {
		return tags;
	}
	/**
	 * @return The dialog message if the sending status is not sent
	 */
	public String getDiag() {
		return diag;
	}
	/**
	 * @return The bounce description if the sending status is bounced or soft-bounced
	 */
	public String getBounceDescription() {
		return bounce_description;
	}

}
