/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

/**
 * <p>An aggregate summary of sending stats.</p>
 * @author rschreijer
 * @since Mar 16, 2013
 */
public class StatsBucket {
	private Stats today, last_7_days, last_30_days;
	private Stats last_60_days, last_90_days, all_time;

	/**
	 * @return The stats so far for today.
	 */
	public Stats getToday() {
		return today;
	}
	/**
	 * @return The stats for the last 7 days.
	 */
	public Stats getLast_7_days() {
		return last_7_days;
	}
	/**
	 * @return The stats for the last 30 days.
	 */
	public Stats getLast_30_days() {
		return last_30_days;
	}
	/**
	 * @return The stats for the last 60 days.
	 */
	public Stats getLast_60_days() {
		return last_60_days;
	}
	/**
	 * @return The stats for the last 90 days.
	 */
	public Stats getLast_90_days() {
		return last_90_days;
	}
	/**
	 * @return The stats for the whole lifetime.
	 */
	public Stats getAll_time() {
		return all_time;
	}

	/**
	 * <p>Stats.</p>
	 * @author rschreijer
	 * @since Mar 16, 2013
	 */
	public static class Stats {
		private Integer sent, hard_bounces, soft_bounces, rejects;
		private Integer complaints, unsubs, opens, clicks;
		private Integer unique_opens, unique_clicks;

		/**
		 * @return The number of emails sent.
		 */
		public Integer getSent() {
			return sent;
		}
		/**
		 * @return The number of emails hard bounced.
		 */
		public Integer getHardBounces() {
			return hard_bounces;
		}
		/**
		 * @return The number of emails soft bounced.
		 */
		public Integer getSoftBounces() {
			return soft_bounces;
		}
		/**
		 * @return The number of emails rejected for sending.
		 */
		public Integer getRejects() {
			return rejects;
		}
		/**
		 * @return The number of spam complaints.
		 */
		public Integer getComplaints() {
			return complaints;
		}
		/**
		 * @return The number of unsubscribes.
		 */
		public Integer getUnsubs() {
			return unsubs;
		}
		/**
		 * @return The number of times emails have been opened.
		 */
		public Integer getOpens() {
			return opens;
		}
		/**
		 * @return The number of URLs that have been clicked.
		 */
		public Integer getClicks() {
			return clicks;
		}
		/**
		 * @return The number of unique opens for emails sent.
		 */
		public Integer getUniqueOpens() {
			return unique_opens;
		}
		/**
		 * @return The number of unique clicks for emails sent.
		 */
		public Integer getUniqueClicks() {
			return unique_clicks;
		}
	}
	
}
