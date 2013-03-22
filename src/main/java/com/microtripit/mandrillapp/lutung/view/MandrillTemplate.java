/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

import java.util.Date;

/**
 * <p>Information about a template.</p>
 * @author rschreijer
 * @since Mar 18, 2013
 */
public class MandrillTemplate {
	private String name, slug, code, publish_name, publish_code;
	private Date published_at, created_at, updated_at;
	
	/**
	 * @return The name of the template.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return The immutable unique code name of the template.
	 */
	public String getSlug() {
		return slug;
	}
	/**
	 * @return The full HTML code of the template, with mc:edit 
	 * attributes marking the editable elements - draft version.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return The same as the template name &ndash; 
	 * kept as a separate field for backwards compatibility
	 */
	public String getPublishName() {
		return publish_name;
	}
	/**
	 * @return The full HTML code of the template, with 
	 * mc:edit attributes marking the editable elements 
	 * that are available as published, if it has been 
	 * published.
	 */
	public String getPublishCode() {
		return publish_code;
	}
	/**
	 * @return The date and time of when this template was 
	 * published, UTC; <code>null</code> if it has not 
	 * been published.
	 */
	public Date getPublishedAt() {
		return published_at;
	}
	/**
	 * @return The date and time of when this template was 
	 * created, UTC.
	 */
	public Date getCreatedAt() {
		return created_at;
	}
	/**
	 * @return The date and time of when this template was 
	 * last modified, UTC.
	 */
	public Date getUpdatedAt() {
		return updated_at;
	}
}
