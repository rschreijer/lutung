/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

import java.util.List;

/**
 * <p>Basic information for a message.</p>
 * @author rschreijer
 * @since Mar 16, 2013
 */
public class MandrillMessageInfo {
    private Long ts;
	private Integer opens, clicks;
	private String _id, sender, template, subject, email, state;
	private List<String> tags;
	private List<UserActionDetail> opens_detail, clicks_detail;
	private List<SMTPEvent> smtp_events;
//	private Map<String,String> metadata;
	
	/**
	 * @return The Unix timestamp from when this message was sent.
	 */
	public Long getTs() {
		return ts;
	}
	/**
	 * @return How many times has this message been opened.
	 */
	public Integer getOpens() {
		return opens;
	}
	/**
	 * @return How many times has a link been clicked in this message.
	 */
	public Integer getClicks() {
		return clicks;
	}
	/**
	 * @return The message's unique id.
	 */
	public String getId() {
		return _id;
	}
	/**
	 * @return The email address of the sender.
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * @return The unique name of the template used, if any.
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @return The message's subject link.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @return The recipient email address.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return The sending status of this message: sent, bounced, rejected.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return List of tags on this message.
	 */
	public List<String> getTags() {
		return tags;
	}
	/**
	 * @return A list of individual opens for the message.
	 */
	public List<UserActionDetail> getOpensDetail() {
		return opens_detail;
	}
	/**
	 * @return A list of individual clicks for the message.
	 */
	public List<UserActionDetail> getClicksDetail() {
		return clicks_detail;
	}
	public List<SMTPEvent> getSmtpEvents() {
		return smtp_events;
	}
//	/**
//	 * @return Any custom metadata provided when the message was sent.
//	 */
//	public Map<String,String> getMetadata() {
//		return metadata;
//	}
	
	public static class UserActionDetail {
		private Integer ts;
		private String url, ip, location, ua;
		
		/**
		 * @return The unix timestamp from when the action occured.
		 */
		public final Integer getTs() {
			return ts;
		}
		/**
		 * @return The URL that was clicked on (clicks only, duh!).
		 */
		public final String getUrl() {
			return url;
		}
		/**
		 * @return The IP address that generated the action.
		 */
		public final String getIp() {
			return ip;
		}
		/**
		 * @return The approximate region and country that the 
		 * acting IP is located.
		 */
		public final String getLocation() {
			return location;
		}
		/**
		 * @return The email client or browser data of the action.
		 */
		public final String getUa() {
			return ua;
		}
	}
	
	public static class SMTPEvent {
		private Integer ts;
		private String type, diag;
		
		/**
		 * @return The Unix timestamp when the event occured.
		 */
		public final Integer getTs() {
			return ts;
		}
		/**
		 * @return The message's state as a result of this event.
		 */
		public final String getType() {
			return type;
		}
		/**
		 * @return The SMTP response from the recipient's server. 
		 */
		public final String getDiag() {
			return diag;
		}
		
		
	}

}
