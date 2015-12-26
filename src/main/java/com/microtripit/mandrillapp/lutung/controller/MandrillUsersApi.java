/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillSender;
import com.microtripit.mandrillapp.lutung.view.MandrillUserInfo;

import java.io.IOException;

/**
 * <p></p>
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillUsersApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillUsersApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}
	
	/**
	 * <p>Get information about the account for the given api key.</p>
	 * @return The information about the API-connected user.
	 * @throws MandrillApiError If the Mandrill API returns an error
	 * @throws IOException
	 */
	public MandrillUserInfo info() throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("users/info.json")
				.execute(MandrillUserInfo.class);
	}
	
	/**
	 * <p>Validate an API key and respond to a ping.</p>
	 * @return The String literal "PONG!"
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public String ping() throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("users/ping.json")
				.execute(String.class);
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
		return queryExecutorFactory.create()
				.path("users/senders.json")
				.execute(MandrillSender[].class);
	}

}
