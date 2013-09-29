/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

import com.microtripit.mandrillapp.lutung.view.StatsBucket.Stats;

/**
 * <p>A user-defined tag w/ attached statistics.</p>
 * @author rschreijer
 * @since Mar 16, 2013
 */
public class MandrillTag extends Stats {
	private String tag;
	private Integer reputation;
	private StatsBucket stats;

	/**
	 * @return The actual tag as a string.
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @return An aggregate summary of the tag's sending stats.
	 */
	public StatsBucket getStats() {
		return stats;
	}
	
	/**
	 * @return The tag's current reputation on a scale from 0 to 100.
	 */
	public Integer getReputation() {
		return reputation;
	}

}
