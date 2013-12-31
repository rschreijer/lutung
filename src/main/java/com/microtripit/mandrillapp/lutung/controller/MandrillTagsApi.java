/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.HashMap;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillTag;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillTagsApi {
	private static final String rootUrl = MandrillUtil.rootUrl;
	private final String key;
	
	public MandrillTagsApi(final String key) {
		this.key = key;
	}

	/**
	 * <p>Access all of the user-defined tag information.</p>
	 * @return All of the user-defined tag information.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTag[] list() 
			throws MandrillApiError, IOException {
		
		return MandrillUtil.query(
				rootUrl+ "tags/list.json", 
				MandrillUtil.paramsWithKey(key), 
				MandrillTag[].class);
		
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
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("tag", tagName);
		return MandrillUtil.query(rootUrl+ "tags/delete.json", 
				params, MandrillTag.class);
		
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
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("tag", tagName);
		return MandrillUtil.query(rootUrl+ "tags/info.json", 
				params, MandrillTag.class);
		
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
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("tag", tagName);
		return MandrillUtil.query(rootUrl+ "tags/time-series.json", 
				params, MandrillTimeSeries[].class);
		
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
		
		return MandrillUtil.query(
				rootUrl+ "tags/all-time-series.json", 
				MandrillUtil.paramsWithKey(key), 
				MandrillTimeSeries[].class);
		
	}
}
