/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.HashMap;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillDomain;
import com.microtripit.mandrillapp.lutung.view.MandrillSender;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public final class MandrillSendersApi {
	private static final String rootUrl = MandrillUtil.rootUrl;
	private final String key;
	
	public MandrillSendersApi(final String key) {
		this.key = key;
	}
	
	/**
	 * <p>Get the senders that have tried to use this account.</p>
	 * @return An array of {@link MandrillSender} objects, one 
	 * for each sending addresses used by the account.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillSender[] list() 
			throws MandrillApiError, IOException {
		
		return MandrillUtil.query(
				rootUrl+ "senders/list.json", 
				MandrillUtil.paramsWithKey(key), 
				MandrillSender[].class);
		
	}
	
	/**
	 * <p>Get the sender domains that have been added to this account.</p>
	 * @return An array of sender domain data, one for each 
	 * sending domain used by the account.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillDomain[] domains() 
			throws MandrillApiError, IOException {

		return MandrillUtil.query(
				rootUrl+ "senders/domains.json", 
				MandrillUtil.paramsWithKey(key), 
				MandrillDomain[].class);

	}
	
	/**
	 * <p>Get more detailed information about a single sender, 
	 * including aggregates of recent stats.</p>
	 * @param address The email address of the sender.
	 * @return The detailed information on the sender.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillSender info(final String address) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("address", address);
		return MandrillUtil.query(rootUrl+ "senders/info.json", 
				params, MandrillSender.class);
		
	}
	
	/**
	 * <p>Get the recent history (hourly stats for 
	 * the last 30 days) for a sender.</p>
	 * @param address The email address of the sender.
	 * @return An array of history information.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillTimeSeries[] timeSeries(final String address) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("address", address);
		return MandrillUtil.query(rootUrl+ "senders/time-series.json", 
				params, MandrillTimeSeries[].class);
		
	}

}
