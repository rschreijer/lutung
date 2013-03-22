/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageInfo;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.microtripit.mandrillapp.lutung.view.MandrillSearchMessageParams;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public final class MandrillMessagesApi {
	private static final String rootUrl = MandrillUtil.rootUrl;
	private final String key;
	
	public MandrillMessagesApi(final String key) {
		this.key = key;
	}
	
	/**
	 * <p>Send a new transactional message through Mandrill.</p>
	 * @param m The information on the message to send
	 * @param async Enable a background sending mode that is optimized 
	 * for bulk sending. In async mode, messages/send will immediately 
	 * return a status of "queued" for every recipient. To handle 
	 * rejections when sending in async mode, set up a webhook for the 
	 * 'reject' event. Defaults to false for messages with no more than 
	 * 10 recipients; messages with more than 10 recipients are always 
	 * sent asynchronously, regardless of the value of async.
	 * @return An array of {@link MandrillMessageStatus} objects, one for 
	 * each recipient containing the key "email" 
	 * ({@link MandrillMessageStatus#getEmail()}) and 
	 * {@link MandrillMessageStatus#getStatus()} as either "sent", 
	 * "queued", or "rejected".
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillMessageStatus[] send(final MandrillMessage m, 
			final Boolean async) throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("message", m);
		params.put("async", async);
		return MandrillUtil.query(rootUrl+ "messages/send.json", 
				params, MandrillMessageStatus[].class);
		
	}
	
	/**
	 * <p>Send a new transactional message through Mandrill 
	 * using a template.</p>
	 * @param templateName The name of a template that exists 
	 * in the user's account; <b>Required</b>.
	 * @param templateContent An map of template content to send. 
	 * Each entry in the map should have the name of the content 
	 * block to set the content for, and the actual content 
	 * to put into the block.
	 * @param m The other information on the message to send &ndash; 
	 * same as {@link #messagesSend(MandrillMessage, Boolean)}, but 
	 * without the html content.
	 * @param async Enable a background sending mode that is optimized 
	 * for bulk sending. In async mode, messages/send will immediately 
	 * return a status of "queued" for every recipient. To handle 
	 * rejections when sending in async mode, set up a webhook for the 
	 * 'reject' event. Defaults to false for messages with no more than 
	 * 10 recipients; messages with more than 10 recipients are always 
	 * sent asynchronously, regardless of the value of async.
	 * @return An array of {@link MandrillMessageStatus} objects, one for 
	 * each recipient containing the key "email" 
	 * ({@link MandrillMessageStatus#getEmail()}) and 
	 * {@link MandrillMessageStatus#getStatus()} as either "sent", 
	 * "queued", or "rejected".
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillMessageStatus[] sendTemplate(
			final String templateName, final Map<String,String> templateContent, 
			final MandrillMessage m, final Boolean async) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("template_name", templateName);
		final ArrayList<TemplateContent> contents;
		if(templateContent == null) {
			contents = null;
		} else {
			contents = new ArrayList<MandrillMessagesApi.TemplateContent>(
					templateContent.size());
			for(String name : templateContent.keySet()) {
				contents.add( TemplateContent.create(
						name, templateContent.get(name)) );
			}
		}
		params.put("template_content", contents);
		params.put("message", m);
		params.put("async", async);
		return MandrillUtil.query(rootUrl+ "messages/send-template.json", 
				params, MandrillMessageStatus[].class);
		
	}
	
	/**
	 * <p>Search the content of recently sent messages and 
	 * optionally narrow by date range, tags and senders.</p>
	 * @param search Search parameters for message searching.
	 * @return An array of {@link MandrillMessageInfo} objects 
	 * containing found messages for the performed search.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillMessageInfo[] search(
			final MandrillSearchMessageParams search) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		if(search != null) {
			params.put("query", search.getQuery());
			params.put("date_from", search.getDateFrom());
			params.put("date_to", search.getDateTo());
			params.put("senders", search.getSenders());
			params.put("tags", search.getTags());
			params.put("limit", search.getLimit());
		}
		return MandrillUtil.query(rootUrl+ "messages/search.json", 
				params,	MandrillMessageInfo[].class);
		
	}
	
	/**
	 * <p>Parse the full MIME document for an email message, 
	 * returning the content of the message broken into 
	 * its constituent pieces.</p>
	 * @param rawMessage The full MIME document of an email message.
	 * @return The parsed message as a {@link MandrillMessage}.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillMessage parse(final String rawMessage) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("raw_message", rawMessage);
		return MandrillUtil.query(rootUrl+ "messages/send.json", 
				params, MandrillMessage.class);
		
	}
	
	/**
	 * 
	 * @param fromEmail
	 * @param fromName
	 * @param rawMessage
	 * @param to
	 * @param async
	 * @return
	 * @throws MandrillApiError
	 * @throws IOException
	 * @since Mar 18, 2013
	 */
	public final MandrillMessageStatus[] sendRaw(final String fromEmail, 
			final String fromName, final String rawMessage, 
			final Collection<String> to, final Boolean async) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("raw_message", rawMessage);
		params.put("from_email", fromEmail);
		params.put("from_name", fromName);
		params.put("to", to);
		params.put("async", async);
		return MandrillUtil.query(rootUrl+ "messages/send-raw.json", 
				params,	MandrillMessageStatus[].class);
		
	}
	
	public static final class TemplateContent {
		private String name, content;
		
		/**
		 * @return The name of the content.
		 */
		public final String getName() {
			return name;
		}
		/**
		 * @return The actual content.
		 */
		public final String getContent() {
			return content;
		}

		protected static final TemplateContent create(
				final String name, final String content) {
			
			final TemplateContent c = new TemplateContent();
			c.name = name;
			c.content = content;
			return c;
		}
	}

}
