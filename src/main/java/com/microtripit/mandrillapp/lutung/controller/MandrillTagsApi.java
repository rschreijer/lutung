/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillTag;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;

import java.io.IOException;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillTagsApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillTagsApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}

	/**
	 * <p>Access all of the user-defined tag information.</p>
	 * @return All of the user-defined tag information.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTag[] list() 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("tags/list.json")
                .execute(MandrillTag[].class);
	}
	
	/**
	 * <p>Delete a tag permanently. Deleting a tag removes the 
	 * tag from any messages that have been sent, and also deletes 
	 * the tag's stats. There is no way to undo this operation, 
	 * so use it carefully.</p>
	 * @param tagName The name of the tag.
	 * @return The tag that was deleted.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTag delete(final String tagName) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("tags/delete.json")
                .addParam("tag", tagName)
                .execute(MandrillTag.class);
	}
	
	/**
	 * <p>Get more detailed information about a single tag, 
	 * including aggregates of recent stats.</p>
	 * @param tagName The name of the tag.
	 * @return Detailed information on the tag.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTag info(final String tagName) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("tags/info.json")
                .addParam("tag", tagName)
                .execute(MandrillTag.class);
	}
	
	/**
	 * <p>Get the recent history (hourly stats for the last 30 
	 * days) for a tag.</p>
	 * @param tagName The name of the tag.
	 * @return The recent history (hourly stats for the last 30 
	 * days) for a tag.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTimeSeries[] timeSeries(final String tagName) 
			throws MandrillApiError, IOException{
		return queryExecutorFactory.create()
                .path("tags/time-series.json")
                .addParam("tag", tagName)
                .execute(MandrillTimeSeries[].class);
	}
	
	/**
	 * <p>Get the recent history (hourly stats for the 
	 * last 30 days) for all tags.</p>
	 * @return The recent history (hourly stats for 
	 * the last 30 days) for all tags.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTimeSeries[] allTimeSeries() 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("tags/all-time-series.json")
                .execute(MandrillTimeSeries[].class);
	}
}
