/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillWebhook;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public final class MandrillWebhooksApi {
	private static final String rootUrl = MandrillUtil.rootUrl;
	private final String key;
	
	public MandrillWebhooksApi(final String key) {
		this.key = key;
	}
	
	/**
	 * <p>Get the list of all webhooks defined for this account.</p>
	 * @return An array of {@link MandrillWebhook} objects.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillWebhook[] list() throws MandrillApiError, 
			IOException {
		
		return MandrillUtil.query(
				rootUrl+ "webhooks/list.json", 
				MandrillUtil.paramsWithKey(key), 
				MandrillWebhook[].class);
		
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
	public final MandrillWebhook add(final String url, final String event) 
			throws MandrillApiError, IOException {
		
		final ArrayList<String> events = new ArrayList<String>(1);
		events.add(event);
		return add(url, null, events);
		
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
	public final MandrillWebhook add(final String url, 
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
	public final MandrillWebhook add(final String url, final String description, 
			final Collection<String> events) throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("url", url);
		params.put("description", description);
		params.put("events", events);
		return MandrillUtil.query(rootUrl+ "webhooks/add.json", 
				params, MandrillWebhook.class);
		
	}
	
	/**
	 * <p>Get the data about an existing webhook.</p>
	 * @param id The unique identifier of a webhook 
	 * belonging to this account.
	 * @return A {@link MandrillWebhook} object with info about the webhook.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillWebhook info(final Integer id) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		return MandrillUtil.query(rootUrl+ "webhooks/info.json", 
				params, MandrillWebhook.class);
		
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
	public final MandrillWebhook update(final Integer id, 
			final String url, final String event) 
					throws MandrillApiError, IOException {
		
		final ArrayList<String> events = new ArrayList<String>(1);
		events.add(event);
		return update(id, url, events);
		
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
	public final MandrillWebhook update(final Integer id, 
			final String url, final Collection<String> events) 
					throws MandrillApiError, IOException {
		
		return update(id, url, null, events);
		
	}
	
	/**
	 * <p>Update an existing webhook.</p>
	 * @param id The unique identifier of a webhook 
	 * belonging to this account.
	 * @param url The URL to POST batches of events.
	 * @param An optional description for the webhook.
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
	public final MandrillWebhook update(final Integer id, final String url, 
			final String description, final Collection<String> events) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		params.put("url", url);
		params.put("description", description);
		params.put("events", events);
		return MandrillUtil.query(rootUrl+ "webhooks/update.json", 
				params, MandrillWebhook.class);
		
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
	public final MandrillWebhook delete(final Integer id) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		return MandrillUtil.query(rootUrl+ "webhooks/delete.json", 
				params, MandrillWebhook.class);
		
	}
}
