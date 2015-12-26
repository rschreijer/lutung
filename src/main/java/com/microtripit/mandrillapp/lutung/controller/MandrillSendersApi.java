/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillDomain;
import com.microtripit.mandrillapp.lutung.view.MandrillDomain.MandrillDomainVerificationInfo;
import com.microtripit.mandrillapp.lutung.view.MandrillSender;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;

import java.io.IOException;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillSendersApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillSendersApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}
	
	/**
	 * <p>Get the senders that have tried to use this account.</p>
	 * @return An array of {@link MandrillSender} objects, one 
	 * for each sending addresses used by the account.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSender[] list() 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("senders/list.json")
                .execute(MandrillSender[].class);
	}
	
	/**
	 * <p>Get the sender domains that have been added to this account.</p>
	 * @return An array of sender domain data, one for each 
	 * sending domain used by the account.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDomain[] domains() 
			throws MandrillApiError, IOException {
        return queryExecutorFactory.create()
                .path("senders/domains.json")
                .execute(MandrillDomain[].class);
	}
	
	/**
	 * <p>Adds a sender domain to your account. Sender domains 
	 * are added automatically as you send, but you can use 
	 * this call to add them ahead of time.</p>
	 * @param domain A domain name.
	 * @return Information about the domain.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDomain addDomain(final String domain) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("senders/add-domain.json")
                .addParam("domain", domain)
                .execute(MandrillDomain.class);
	}
	
	/**
	 * <p>Checks the SPF and DKIM settings for a domain. If you 
	 * haven't already added this domain to your account, 
	 * it will be added automatically.</p>
	 * @param domain A domain name.
	 * @return Information about the sender domain.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDomain checkDomain(final String domain) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("senders/check-domain.json")
                .addParam("domain", domain)
                .execute(MandrillDomain.class);
	}
	
	/**
	 * <p>Sends a verification email in order to verify ownership 
	 * of a domain. Domain verification is an optional step to 
	 * confirm ownership of a domain. Once a domain has been verified 
	 * in a Mandrill account, other accounts may not have their 
	 * messages signed by that domain unless they also verify the 
	 * domain. This prevents other Mandrill accounts from sending 
	 * mail signed by your domain.</p>
	 * @param domain A domain name at which you can receive email.
	 * @param mailbox A mailbox at the domain where the verification 
	 * email should be sent.
	 * @return Info about the verification email that was sent.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDomainVerificationInfo verifyDomain(
			final String domain, final String mailbox) 
					throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("senders/verify-domain.json")
                .addParam("domain", domain)
                .addParam("mailbox", mailbox)
                .execute(MandrillDomainVerificationInfo.class);
	}
	
	/**
	 * <p>Get more detailed information about a single sender, 
	 * including aggregates of recent stats.</p>
	 * @param address The email address of the sender.
	 * @return The detailed information on the sender.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSender info(final String address) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("senders/info.json")
                .addParam("address", address)
                .execute(MandrillSender.class);
	}
	
	/**
	 * <p>Get the recent history (hourly stats for 
	 * the last 30 days) for a sender.</p>
	 * @param address The email address of the sender.
	 * @return An array of history information.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTimeSeries[] timeSeries(final String address) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("senders/time-series.json")
                .addParam("address", address)
                .execute(MandrillTimeSeries[].class);
	}
}
