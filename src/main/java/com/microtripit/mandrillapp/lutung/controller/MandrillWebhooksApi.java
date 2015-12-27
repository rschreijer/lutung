/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillWebhook;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillWebhooksApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillWebhooksApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}
	
	/**
	 * <p>Get the list of all webhooks defined for this account.</p>
	 * @return An array of {@link MandrillWebhook} objects.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillWebhook[] list() throws MandrillApiError, 
			IOException {
		return queryExecutorFactory.create()
                .path("webhooks/list.json")
                .execute(MandrillWebhook[].class);
	}
	
	/**
	 * <p>Add a new webhook.</p>
	 * @param url The URL to POST batches of events.
	 * @param event An optional event that will be posted 
	 * to the webhook. You can use
	 * {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * as valid events.
	 * @return A {@link MandrillWebhook} object with info about the new webhook.
	 * @throws MandrillApiError
	 * @throws IOException
	 * @see {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * for valid events.
	 */
	public MandrillWebhook add(final String url, final String event) 
			throws MandrillApiError, IOException {
		return add(url, null, Arrays.asList(event));
	}
	
	/**
	 * <p>Add a new webhook.</p>
	 * @param url The URL to POST batches of events.
	 * @param events An optional collection of events 
	 * that will be posted to the webhook. You can use
	 * {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * as valid events.
	 * @return A {@link MandrillWebhook} object with info about the new webhook.
	 * @throws MandrillApiError
	 * @throws IOException
	 * @see {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * for valid events.
	 */
	public MandrillWebhook add(final String url, 
			final Collection<String> events) throws MandrillApiError, 
			IOException {
		return add(url, null, events);
	}
	
	/**
	 * <p>Add a new webhook.</p>
	 * @param url The URL to POST batches of events.
	 * @param description An optional description of the webhook.
	 * @param events An optional array of events that will 
	 * be posted to the webhook. You can use
	 * {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * as valid events.
	 * @return A {@link MandrillWebhook} object with info about the new webhook.
	 * @throws MandrillApiError
	 * @throws IOException
	 * @see {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * for valid events.
	 */
	public MandrillWebhook add(final String url, final String description, 
			final Collection<String> events) throws MandrillApiError, IOException {
	    return queryExecutorFactory.create()
                .path("webhooks/add.json")
                .addParam("url", url)
                .addParam("description", description)
                .addParam("events", events)
                .execute(MandrillWebhook.class);
	}
	
	/**
	 * <p>Get the data about an existing webhook.</p>
	 * @param id The unique identifier of a webhook 
	 * belonging to this account.
	 * @return A {@link MandrillWebhook} object with info about the webhook.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillWebhook info(final Integer id) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("webhooks/info.json")
                .addParam("id", id)
                .execute(MandrillWebhook.class);
	}
	
	/**
	 * <p>Update an existing webhook.</p>
	 * @param id The unique identifier of a webhook 
	 * belonging to this account.
	 * @param url The URL to POST batches of events.
	 * @param event An optional events that will be posted 
	 * to the webhook. You can use
	 * {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * as valid events.
	 * @return A {@link MandrillWebhook} object with info about the webhook.
	 * @throws MandrillApiError
	 * @throws IOException
	 * @see {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * for valid events.
	 */
	public MandrillWebhook update(final Integer id, 
			final String url, final String event) 
					throws MandrillApiError, IOException {
		return update(id, url, Arrays.asList(event));
	}
	
	/**
	 * <p>Update an existing webhook.</p>
	 * @param id The unique identifier of a webhook 
	 * belonging to this account.
	 * @param url The URL to POST batches of events.
	 * @param events An optional collection of events 
	 * that will be posted to the webhook. You can use
	 * {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * as valid events.
	 * @return A {@link MandrillWebhook} object with info about the webhook.
	 * @throws MandrillApiError
	 * @throws IOException
	 * @see {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * for valid events.
	 */
	public MandrillWebhook update(final Integer id, 
			final String url, final Collection<String> events) 
					throws MandrillApiError, IOException {
		return update(id, url, null, events);
	}
	
	/**
	 * <p>Update an existing webhook.</p>
	 * @param id The unique identifier of a webhook 
	 * belonging to this account.
	 * @param url The URL to POST batches of events.
	 * @param description optional description for the webhook.
	 * @param events An optional collection of events 
	 * that will be posted to the webhook. You can use
	 * {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * as valid events.
	 * @return A {@link MandrillWebhook} object with info about the webhook.
	 * @throws MandrillApiError
	 * @throws IOException
	 * @see {@link MandrillWebhook#SEND}, {@link MandrillWebhook#HARD_BOUNCE}, 
	 * {@link MandrillWebhook#SOFT_BOUNCE}, {@link MandrillWebhook#OPEN}, 
	 * {@link MandrillWebhook#CLICK}, {@link MandrillWebhook#SPAM}, 
	 * {@link MandrillWebhook#UNSUB} and {@link MandrillWebhook#REJECT} 
	 * for valid events.
	 */
	public MandrillWebhook update(final Integer id, final String url, 
			final String description, final Collection<String> events) 
					throws MandrillApiError, IOException {
	    return queryExecutorFactory.create()
                .path("webhooks/update.json")
                .addParam("id", id)
                .addParam("url", url)
                .addParam("description", description)
                .addParam("events", events)
                .execute(MandrillWebhook.class);
	}
	
	/**
	 * <p>Delete an existing webhook.</p>
	 * @param id The unique identifier of a webhook 
	 * belonging to this account.
	 * @return A {@link MandrillWebhook} object with info 
	 * about the just deleted webhook.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillWebhook delete(final Integer id) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("webhooks/delete.json")
                .addParam("id", id)
                .execute(MandrillWebhook.class);
	}
}
