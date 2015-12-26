/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillInboundDomain;
import com.microtripit.mandrillapp.lutung.view.MandrillInboundRecipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMailboxRoute;

import java.io.IOException;
import java.util.Collection;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillInboundApi {
    private final QueryExecutorFactory queryExecutorFactory;

	public MandrillInboundApi(final QueryExecutorFactory queryExecutorFactory) {
        this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
    }

	/**
	 * <p>List the domains that have been configured for 
	 * inbound delivery.</p>
	 * @return The inbound domains associated with the account.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	 MandrillInboundDomain[] domains() 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("inbound/domains.json")
                .execute(MandrillInboundDomain[].class);
	}
	
	/**
	 * <p>List the mailbox routes defined for an inbound domain.</p>
	 * @param domain The domain to check.
	 * @return The routes associated with the domain.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	 MandrillMailboxRoute[] routes(final String domain)
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("inbound/routes.json")
                .addParam("domain", domain)
                .execute(MandrillMailboxRoute[].class);
	}
	
	/**
	 * <p>Take a raw MIME document destined for a domain with inbound 
	 * domains set up, and send it to the inbound hook exactly as if 
	 * it had been sent over SMTP $sparam string $to[] the email address 
	 * of the recipient.</p>
	 * @param rawMessage A full MIME document of an email message.
	 * @param to Optionally define the recipients to receive the 
	 * message &ndash; otherwise we'll use the To, Cc, and Bcc headers 
	 * provided in the document.
	 * @return An array of the information for each recipient in the 
	 * message (usually one) that matched an inbound route.
	 * @throws MandrillApiError
	 * @throws IOException
	 * @since Mar 19, 2013
	 */
	 MandrillInboundRecipient[] sendRaw(
			final String rawMessage, final Collection<String> to) 
					throws MandrillApiError, IOException {
		
		return sendRaw(rawMessage, to, null, null, null);
		
	}
	
	/**
	 * <p>Take a raw MIME document destined for a domain with inbound 
	 * domains set up, and send it to the inbound hook exactly as if 
	 * it had been sent over SMTP $sparam string $to[] the email address 
	 * of the recipient.</p>
	 * @param rawMessage A full MIME document of an email message.
	 * @param to Optionally define the recipients to receive the 
	 * message &ndash; otherwise we'll use the To, Cc, and Bcc headers 
	 * provided in the document.
	 * @param mailFrom The address specified in the MAIL FROM stage 
	 * of the SMTP conversation. Required for the SPF check.
	 * @param helo The identification provided by the client mta in 
	 * the MTA state of the SMTP conversation. Required for the SPF check.
	 * @param clientAddress The remote MTA's ip address. Optional; 
	 * required for the SPF check.
	 * @return An array of the information for each recipient in the 
	 * message (usually one) that matched an inbound route.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	 MandrillInboundRecipient[] sendRaw(final String rawMessage, 
			final Collection<String> to, final String mailFrom, 
			final String helo, final String clientAddress) 
					throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("inbound/send-raw.json")
                .addParam("raw_message", rawMessage)
                .addParam("to", to)
                .addParam("mail_from", mailFrom)
                .addParam("helo", helo)
                .addParam("client_address", clientAddress)
                .execute(MandrillInboundRecipient[].class);
		
	}
}
