/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;
import com.microtripit.mandrillapp.lutung.view.MandrillUrl;

import java.io.IOException;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillUrlsApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillUrlsApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}
	
	/**
	 * <p>Get the 100 most clicked URLs.</p>
	 * @return
	 * @throws MandrillApiError
	 * @throws IOException
	 * @since Mar 19, 2013
	 */
	public MandrillUrl[] list() 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("urls/list.json")
				.execute(MandrillUrl[].class);
	}
	
	/**
	 * <p>Get the 100 most clicked URLs that match 
	 * the search query given.</p>
	 * @param query A search query.
	 * @return An array of {@link MandrillUrl} objects with 
	 * the 100 most clicked URLs matching the search query.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillUrl[] search(final String query) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("urls/search.json")
				.addParam("q", query)
				.execute(MandrillUrl[].class);
	}
	
	/**
	 * <p>Get the recent history (hourly stats for the 
	 * last 30 days) for a url.</p>
	 * @param url An existing URL.
	 * @return An array of {@link MandrillTimeSeries} objects.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTimeSeries[] timeSeries(final String url) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("urls/time-series.json")
				.addParam("url", url)
				.execute(MandrillTimeSeries[].class);
	}
}
