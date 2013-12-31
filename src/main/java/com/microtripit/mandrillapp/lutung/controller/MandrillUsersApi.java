/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillSender;
import com.microtripit.mandrillapp.lutung.view.MandrillUserInfo;

/**
 * <p></p>
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillUsersApi {
	private static final String rootUrl = MandrillUtil.rootUrl;
	private final String key;
	
	public MandrillUsersApi(final String key) {
		this.key = key;
	}
	
	/**
	 * <p>Get information about the account for the given api key.</p>
	 * @return The information about the API-connected user.
	 * @throws MandrillApiError If the Mandrill API returns an error
	 * @throws IOException
	 */
	public MandrillUserInfo info() throws MandrillApiError, IOException {
		return MandrillUtil.query(rootUrl+ "users/info.json", 
				MandrillUtil.paramsWithKey(key), MandrillUserInfo.class);
		
	}
	
	/**
	 * <p>Validate an API key and respond to a ping.</p>
	 * @return The String literal "PONG!"
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public String ping() throws MandrillApiError, IOException {
		return MandrillUtil.query(rootUrl+ "users/ping.json", 
				MandrillUtil.paramsWithKey(key), String.class);
		
	}
	
	/**
	 * <p>Return the senders that have tried to use this account, 
	 * both verified and unverified.</p>
	 * @return The senders that have tried to use this account, 
	 * both verified and unverified.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSender[] senders() throws MandrillApiError, 
			IOException {
		
		return MandrillUtil.query(rootUrl+ "users/senders.json", 
				MandrillUtil.paramsWithKey(key), MandrillSender[].class);
		
	}

}
