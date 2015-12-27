/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillMessagesApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillMessagesApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}
	
	/**
	 * <p>Send a new transactional message through Mandrill.</p>
	 * @param m The information on the message to send
	 * @param async Enable a background sending mode that is optimized 
	 * for bulk sending. In async mode, messages/send will immediately 
	 * return a status of 'queued' for every recipient. To handle 
	 * rejections when sending in async mode, set up a webhook for the 
	 * 'reject' event. Defaults to false for messages with no more than 
	 * 10 recipients; messages with more than 10 recipients are always 
	 * sent asynchronously, regardless of the value of async.
	 * @return An array of {@link MandrillMessageStatus} objects, one for 
	 * each recipient containing the key 'email' 
	 * ({@link MandrillMessageStatus#getEmail()}) and 
	 * {@link MandrillMessageStatus#getStatus()} as either 'sent', 
	 * 'queued', or 'rejected'.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillMessageStatus[] send(final MandrillMessage m, 
			final Boolean async) throws MandrillApiError, IOException {
	
		return send(m, async, null, null);
		
	}
	
	/**
	 * <p>Send a new transactional message through Mandrill.</p>
	 * @param m The information on the message to send
	 * @param async Enable a background sending mode that is optimized 
	 * for bulk sending. In async mode, messages/send will immediately 
	 * return a status of 'queued' for every recipient. To handle 
	 * rejections when sending in async mode, set up a webhook for the 
	 * 'reject' event. Defaults to false for messages with no more than 
	 * 10 recipients; messages with more than 10 recipients are always 
	 * sent asynchronously, regardless of the value of async.
	 * @param ipPool The name of the dedicated ip pool that should be 
	 * used to send the message. If you do not have any dedicated IPs, 
	 * this parameter has no effect. If you specify a pool that does 
	 * not exist, your default pool will be used instead. Set to 
	 * <code>null</code> to ignore this parameter.
	 * @param sendAt When this message should be sent. If you specify a 
	 * time in the past, the message will be sent immediately. An 
	 * additional fee applies for scheduled email, and this feature is 
	 * only available to accounts with a positive balance. Set to 
	 * <code>null</code> to ignore this parameter.
	 * @return An array of {@link MandrillMessageStatus} objects, one for 
	 * each recipient containing the key 'email' 
	 * ({@link MandrillMessageStatus#getEmail()}) and 
	 * {@link MandrillMessageStatus#getStatus()} as either 'sent', 
	 * 'queued', or 'rejected'.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillMessageStatus[] send(final MandrillMessage m, 
			final Boolean async, final String ipPool, final Date sendAt) 
					throws MandrillApiError, IOException {
        return queryExecutorFactory.create()
                .path("messages/send.json")
                .addParam("message", m)
                .addParam("async", async)
                .addParamIfNotNull("ip_pool", ipPool)
                .addParamIfNotNull("send_at", sendAt)
                .execute(MandrillMessageStatus[].class);
	}
	
	
	
	/**
	 * <p>Send a new transactional message through Mandrill 
	 * using a template.</p>
	 * @param templateName The name of a template that exists 
	 * in the user's account; <b>Required</b>.
	 * @param templateContent A map of template content to send. 
	 * Each entry in the map should have the name of the content 
	 * block to set the content for, corresponding to an mc:edit block,
	 * and the actual content to put into the block. May be null.
	 * @param m The other information on the message to send &ndash; 
	 * same as {@link #send(MandrillMessage, Boolean)}, but without 
	 * the html content.
	 * @param async Enable a background sending mode that is optimized 
	 * for bulk sending. In async mode, messages/send will immediately 
	 * return a status of 'queued' for every recipient. To handle 
	 * rejections when sending in async mode, set up a webhook for the 
	 * 'reject' event. Defaults to false for messages with no more than 
	 * 10 recipients; messages with more than 10 recipients are always 
	 * sent asynchronously, regardless of the value of async.
	 * @return An array of {@link MandrillMessageStatus} objects, one for 
	 * each recipient containing the key 'email' 
	 * ({@link MandrillMessageStatus#getEmail()}) and 
	 * {@link MandrillMessageStatus#getStatus()} as either 'sent', 
	 * 'queued', or 'rejected'.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillMessageStatus[] sendTemplate(
			final String templateName, final Map<String,String> templateContent, 
			final MandrillMessage m, final Boolean async) 
					throws MandrillApiError, IOException {
	
		return sendTemplate(templateName, templateContent, m, 
				async, null, null);
		
	}
	
	/**
	 * <p>Send a new transactional message through Mandrill 
	 * using a template.</p>
	 * @param templateName The name of a template that exists 
	 * in the user's account; <b>Required</b>.
	 * @param templateContent A map of template content to send. 
	 * Each entry in the map should have the name of the content 
	 * block to set the content for, corresponding to an mc:edit block,
	 * and the actual content to put into the block. May be null.
	 * @param m The other information on the message to send &ndash; 
	 * same as {@link #send(MandrillMessage, Boolean)}, but without 
	 * the html content.
	 * @param async Enable a background sending mode that is optimized 
	 * for bulk sending. In async mode, messages/send will immediately 
	 * return a status of 'queued' for every recipient. To handle 
	 * rejections when sending in async mode, set up a webhook for the 
	 * 'reject' event. Defaults to false for messages with no more than 
	 * 10 recipients; messages with more than 10 recipients are always 
	 * sent asynchronously, regardless of the value of async.
	 * @param ipPool The name of the dedicated ip pool that should be 
	 * used to send the message. If you do not have any dedicated IPs, 
	 * this parameter has no effect. If you specify a pool that does 
	 * not exist, your default pool will be used instead. Set to 
	 * <code>null</code> to ignore this parameter.
	 * @param sendAt When this message should be sent. If you specify a 
	 * time in the past, the message will be sent immediately. An 
	 * additional fee applies for scheduled email, and this feature is 
	 * only available to accounts with a positive balance. Set to 
	 * <code>null</code> to ignore this parameter.
	 * @return An array of {@link MandrillMessageStatus} objects, one for 
	 * each recipient containing the key 'email' 
	 * ({@link MandrillMessageStatus#getEmail()}) and 
	 * {@link MandrillMessageStatus#getStatus()} as either 'sent', 
	 * 'queued', or 'rejected'.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillMessageStatus[] sendTemplate(
			final String templateName, final Map<String,String> templateContent, 
			final MandrillMessage m, final Boolean async, 
			final String ipPool, final Date sendAt) 
					throws MandrillApiError, IOException {
                final ArrayList<TemplateContent> contents;
		if(templateContent == null) {
            contents = new ArrayList<MandrillMessagesApi.TemplateContent>(1);
            // API requires at least one entry in the template_content array, even when unused
            contents.add( TemplateContent.create("satisfy_validation", "") );
        }
        else {
            contents = new ArrayList<MandrillMessagesApi.TemplateContent>(
					templateContent.size());
			for(String name : templateContent.keySet()) {
				contents.add( TemplateContent.create(
						name, templateContent.get(name)) );
			}
		}
        return  queryExecutorFactory.create()
                .path("messages/send-template.json")
                .addParam("template_name", templateName)
                .addParam("template_content", contents)
                .addParam("message", m)
                .addParam("async", async)
                .addParamIfNotNull("ip_pool", ipPool)
                .addParamIfNotNull("send_at", sendAt)
                .execute(MandrillMessageStatus[].class );
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
	public MandrillMessageInfo[] search(
			final MandrillSearchMessageParams search) 
					throws MandrillApiError, IOException {
        final QueryExecutor queryExecutor = queryExecutorFactory.create().path("messages/search.json");
		if(search != null) {
            queryExecutor.addParam("query", search.getQuery())
                    .addParam("date_from", search.getDateFrom())
                    .addParam("date_to", search.getDateTo())
                    .addParam("tags", search.getTags())
                    .addParam("senders", search.getSenders())
                    .addParam("api_keys", search.getApiKeys())
                    .addParam("limit", search.getLimit());
		}
		return queryExecutor.execute(MandrillMessageInfo[].class);
	}
	
	/**
	 * <p>Search the content of recently sent messages and return 
	 * the aggregated hourly stats for matching messages.</p>
	 * @param search Search parameters for message searching.
	 * @return An array of {@link MandrillTimeSeries} objects 
	 * containing aggregated hourly stats for the matching messages.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTimeSeries[] searchTimeSeries(
			final MandrillSearchMessageParams search) 
					throws MandrillApiError, IOException {
        final QueryExecutor queryExecutor = queryExecutorFactory.create()
                .path("messages/search-time-series.json");
		if(search != null) {
			queryExecutor.addParam("query", search.getQuery())
                    .addParam("date_from", search.getDateFrom())
                    .addParam("date_to", search.getDateTo())
                    .addParam("tags", search.getTags())
                    .addParam("senders", search.getSenders());
		}
		return queryExecutor.execute(MandrillTimeSeries[].class);
		
	}
	
	/**
	 * <p>Get the information for a single recently sent message.</p>
	 * @param id The unique id of the message to get &ndash; passed as 
	 * the '_id' field in webhooks, send calls, or search calls.
	 * @return The information for the message.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillMessageInfo info(final String id) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("messages/info.json")
                .addParam("id", id)
                .execute(MandrillMessageInfo.class);
	}

    /**
     * <p>Get the content of a single recently sent message.</p>
     * @param id The unique id of the message to get &ndash; passed as
     * the '_id' field in webhooks, send calls, or search calls.
     * @return The content of the message.
     * @throws MandrillApiError
     * @throws IOException
     */
    public MandrillMessageContent content(final String id)
            throws MandrillApiError, IOException {
        return queryExecutorFactory.create()
                .path("messages/content.json")
                .addParam("id", id)
                .execute(MandrillMessageContent.class);
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
	public MandrillMessage parse(final String rawMessage) 
			throws MandrillApiError, IOException {
        return queryExecutorFactory.create()
                .path("messages/parse.json")
                .addParam("raw_message", rawMessage)
                .execute(MandrillMessage.class);
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
	public MandrillMessageStatus[] sendRaw(final String fromEmail, 
			final String fromName, final String rawMessage, 
			final Collection<String> to, final Boolean async) 
					throws MandrillApiError, IOException {
		
		return sendRaw(fromEmail, fromName, rawMessage, to, async, 
				null, null, null);
		
	}
	
	public MandrillMessageStatus[] sendRaw(final String fromEmail, 
			final String fromName, final String rawMessage, 
			final Collection<String> to, final Boolean async,
			final String ipPool, final Date sendAt, 
			final String returnPathDomain) 
					throws MandrillApiError, IOException {
        return queryExecutorFactory.create()
                .path("messages/send-raw.json")
                .addParam("raw_message", rawMessage)
                .addParam("from_email", fromEmail)
                .addParam("from_name", fromName)
                .addParam("to", to)
                .addParam("async", async)
                .addParamIfNotNull("ip_pool", ipPool)
                .addParamIfNotNull("send_at", sendAt)
                .addParamIfNotNull("return_path_domain", returnPathDomain)
                .execute(MandrillMessageStatus[].class);
	}
	
	/**
	 * <p>Queries your scheduled emails by sender or recipient, or both.</p>
	 * @param to An optional recipient address to restrict results to.
	 * @return A list of up to 1000 scheduled emails.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillScheduledMessageInfo[] listScheduled(final String to) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("messages/list-scheduled.json")
                .addParam("to", to)
                .execute(MandrillScheduledMessageInfo[].class);
	}
	
	/**
	 * <p>Cancels a scheduled email.</p>
	 * @param id A scheduled email id, as returned by any of 
	 * the messages/send calls or messages/list-scheduled.
	 * @return Information about the scheduled email that was cancelled.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillScheduledMessageInfo cancelScheduled(final String id) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("messages/cancel-scheduled.json")
                .addParam("id", id)
                .execute(MandrillScheduledMessageInfo.class);
	}
	
	/**
	 * <p>Reschedules a scheduled email.</p>
	 * @param id A scheduled email id, as returned by any of 
	 * the messages/send calls or messages/list-scheduled.
	 * @param send_at The new UTC timestamp when the message should 
	 * sent. Mandrill can't time travel, so if you specify a time in 
	 * past the message will be sent immediately.
	 * @return Information about the scheduled email that was cancelled.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillScheduledMessageInfo reschedule(final String id, 
			final Date send_at) throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("messages/reschedule.json")
                .addParam("id", id)
                .addParam("send_at", send_at)
                .execute(MandrillScheduledMessageInfo.class);
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
