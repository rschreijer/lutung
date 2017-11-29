/**
 * 
 */
package com.microtripit.mandrillapp.lutung;

import com.microtripit.mandrillapp.lutung.controller.MandrillExportsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillInboundApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillIpsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillMessagesApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillRejectsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillSendersApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillSubaccountsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillTagsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillTemplatesApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillUrlsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillUsersApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillWebhooksApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillWhitelistsApi;

/**
 * @author rschreijer
 * @since Mar 17, 2013
 */
public class MandrillApi {
	public static final String rootUrl = "https://mandrillapp.com/api/1.0/";

	private String key;
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
	
	public MandrillApi(final String key) {
		this(key, rootUrl);
	}
	
	public MandrillApi(final String key, final String rootUrl) {
		if(key == null) {
			throw new NullPointerException(
					"'key' is null; please provide Mandrill API key");
		}
		if(rootUrl == null) {
			throw new NullPointerException(
					String.format("'rootUrl' is null; please provide Mandrill URL (default: %s)", rootUrl));
		}
		this.key = key;
		users = new MandrillUsersApi(key, rootUrl);
		messages = new MandrillMessagesApi(key, rootUrl);
		tags = new MandrillTagsApi(key, rootUrl);
		rejects = new MandrillRejectsApi(key, rootUrl);
		whitelists = new MandrillWhitelistsApi(key, rootUrl);
		senders = new MandrillSendersApi(key, rootUrl);
		urls = new MandrillUrlsApi(key, rootUrl);
		templates = new MandrillTemplatesApi(key, rootUrl);
		webhooks = new MandrillWebhooksApi(key, rootUrl);
		subaccounts = new MandrillSubaccountsApi(key, rootUrl);
		inbound = new MandrillInboundApi(key, rootUrl);
		exports = new MandrillExportsApi(key, rootUrl);
		ips = new MandrillIpsApi(key, rootUrl);
	}

	/**
	 * @return Your Mandrill API key.
	 */
	public String getKey() {
		return key;
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
