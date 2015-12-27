/**
 * 
 */
package com.microtripit.mandrillapp.lutung;

import com.microtripit.mandrillapp.lutung.controller.*;
import com.microtripit.mandrillapp.lutung.http.MandrillRequestDispatcher;
import com.microtripit.mandrillapp.lutung.http.MandrillService;

/**
 * @author rschreijer
 * @since Mar 17, 2013
 */
public class MandrillApi {
	private final MandrillUsersApi users;
	private final MandrillMessagesApi messages;
	private final MandrillTagsApi tags;
	private final MandrillRejectsApi rejects;
	private final MandrillWhitelistsApi whitelists;
	private final MandrillSendersApi senders;
	private final MandrillUrlsApi urls;
	private final MandrillTemplatesApi templates;
	private final MandrillWebhooksApi webhooks;
	private final MandrillSubaccountsApi subaccounts;
	private final MandrillInboundApi inbound;
	private final MandrillExportsApi exports;
	private final MandrillIpsApi ips;
	
	public MandrillApi(final String key, final String rootUrl) {
		if(key == null) {
			throw new NullPointerException(
					"'key' is null; please provide Mandrill API key");
			
		}
		final MandrillService mandrillService = new MandrillService(rootUrl, new MandrillRequestDispatcher(), key);
		final QueryExecutorFactory queryExecutorFactory = new QueryExecutorFactory(mandrillService);
		users = new MandrillUsersApi(queryExecutorFactory);
		messages = new MandrillMessagesApi(queryExecutorFactory);
		tags = new MandrillTagsApi(queryExecutorFactory);
		rejects = new MandrillRejectsApi(queryExecutorFactory);
		whitelists = new MandrillWhitelistsApi(queryExecutorFactory);
		senders = new MandrillSendersApi(queryExecutorFactory);
		urls = new MandrillUrlsApi(queryExecutorFactory);
		templates = new MandrillTemplatesApi(queryExecutorFactory);
		webhooks = new MandrillWebhooksApi(queryExecutorFactory);
		subaccounts = new MandrillSubaccountsApi(queryExecutorFactory);
		inbound = new MandrillInboundApi(queryExecutorFactory);
		exports = new MandrillExportsApi(queryExecutorFactory);
		ips = new MandrillIpsApi(queryExecutorFactory);
	}

	/**
	 * <p>Get access to 'users' calls.</p>
	 * @return An object with access to user calls.
	 */
	public MandrillUsersApi users() {
		return users;
	}
	
	public MandrillMessagesApi messages() {
		return messages;
	}
	
	public MandrillTagsApi tags() {
		return tags;
	}
	
	public MandrillRejectsApi rejects() {
		return rejects;
	}
	
	public MandrillWhitelistsApi whitelists() {
		return whitelists;
	}
	
	public MandrillSendersApi senders() {
		return senders;
	}
	
	public MandrillUrlsApi urls() {
		return urls;
	}
	
	public MandrillTemplatesApi templates() {
		return templates;
	}
	
	public MandrillWebhooksApi webhooks() {
		return webhooks;
	}
	
	public MandrillSubaccountsApi subaccounts() {
		return subaccounts;
	}
	
	public MandrillInboundApi inbound() {
		return inbound;
	}
	
	public MandrillExportsApi exports() {
		return exports;
	}
	
	public MandrillIpsApi ips() {
		return ips;
	}
	
}
