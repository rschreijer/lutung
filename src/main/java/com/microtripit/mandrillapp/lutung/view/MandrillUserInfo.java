/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

import java.util.Date;

/**
 * @author rschreijer
 * @since Mar 16, 2013
 */
public class MandrillUserInfo {
	private String username, public_id;
	private Date created_at;
	private Integer reputation, hourly_quota, backlog;
	private StatsBucket stats;
	
	/**
	 * @return The username of the user (used for SMTP authentication).
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return The date and time that the user's Mandrill account was 
	 * created (UTC).
	 */
	public Date getCreatedAt() {
		return created_at;
	}

	/**
	 * @return A unique, permanent identifier for this user.
	 */
	public String getPublicId() {
		return public_id;
	}

	/**
	 * @return The reputation of the user on a scale from 0 
	 * to 100, with 75 generally being a "good" reputation.
	 */
	public Integer getReputation() {
		return reputation;
	}

	/**
	 * @return The maximum number of emails Mandrill will deliver 
	 * for this user each hour. Any emails beyond that will be 
	 * accepted and queued for later delivery. Users with higher 
	 * reputations will have higher hourly quotas.
	 */
	public Integer getHourlyQuota() {
		return hourly_quota;
	}

	/**
	 * @return The number of emails that are queued for delivery 
	 * due to exceeding your monthly or hourly quotas.
	 */
	public Integer getBacklog() {
		return backlog;
	}

	/**
	 * @return An aggregate summary of the account's sending stats.
	 */
	public StatsBucket getStats() {
		return stats;
	}
}
