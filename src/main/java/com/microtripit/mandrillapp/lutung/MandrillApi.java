/**
 * 
 */
package com.microtripit.mandrillapp.lutung;

import com.microtripit.mandrillapp.lutung.controller.MandrillInboundApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillMessagesApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillRejectsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillSendersApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillTagsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillTemplatesApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillUrlsApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillUsersApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillWebhooksApi;

/**
 * @author rschreijer
 * @since Mar 17, 2013
 */
public class MandrillApi {
	private String key;
	private final MandrillUsersApi users;
	private final MandrillMessagesApi messages;
	private final MandrillTagsApi tags;
	private final MandrillRejectsApi rejects;
	private final MandrillSendersApi senders;
	private final MandrillUrlsApi urls;
	private final MandrillTemplatesApi templates;
	private final MandrillWebhooksApi webhooks;
	private final MandrillInboundApi inbound;
	
	public MandrillApi(final String key) {
		if(key == null) {
			throw new NullPointerException(
					"'key' is null; please provide Mandrill API key");
			
		}
		this.key = key;
		users = new MandrillUsersApi(key);
		messages = new MandrillMessagesApi(key);
		tags = new MandrillTagsApi(key);
		rejects = new MandrillRejectsApi(key);
		senders = new MandrillSendersApi(key);
		urls = new MandrillUrlsApi(key);
		templates = new MandrillTemplatesApi(key);
		webhooks = new MandrillWebhooksApi(key);
		inbound = new MandrillInboundApi(key);
	}

	/**
	 * @return Your Mandrill API key.
	 */
	public final String getKey() {
		return key;
	}
	
	/**
	 * <p>Get access to 'users' calls.</p>
	 * @return An object with access to user calls.
	 */
	public final MandrillUsersApi users() {
		return users;
	}
	
	public final MandrillMessagesApi messages() {
		return messages;
	}
	
	public final MandrillTagsApi tags() {
		return tags;
	}
	
	public final MandrillRejectsApi rejects() {
		return rejects;
	}
	
	public final MandrillSendersApi senders() {
		return senders;
	}
	
	public final MandrillUrlsApi urls() {
		return urls;
	}
	
	public final MandrillTemplatesApi templates() {
		return templates;
	}
	
	public final MandrillWebhooksApi webhooks() {
		return webhooks;
	}
	
	public final MandrillInboundApi inbound() {
		return inbound;
	}
	
}
