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
	private String name, slug, code, subject, from_email, from_name, text,
			publish_name, publish_code, publish_subject, publish_from_email,
			publish_from_name, publish_text;
	private Date published_at, created_at, updated_at;

	private String[] labels;

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
	 * @return The subject line of the template, if provided
	 * &ndash; draft version.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @return The default sender address for the template,
	 * if provided &ndash; draft version
	 */
	public String getFromEmail() {
		return from_email;
	}
	/**
	 * @return The default sender from name for the template,
	 * if provided &ndash; draft version
	 */
	public String getFromName() {
		return from_name;
	}
	/**
	 * @return The default text part of messages sent with the
	 * template, if provided &ndash; draft version
	 */
	public String getText() {
		return text;
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
	 * @return The subject line of the template, if provided.
	 */
	public String getPublishSubject() {
		return publish_subject;
	}
	/**
	 * @return The default sender address for the template, if provided.
	 */
	public String getPublishFromEmail() {
		return publish_from_email;
	}
	/**
	 * @return The default sender from name for the template, if provided.
	 */
	public String getPublishFromName() {
		return publish_from_name;
	}
	/**
	 * @return The default text part of messages sent with the
	 * template, if provided.
	 */
	public String getPublishText() {
		return publish_text;
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
	/**
	 * @return The labels associated with this template
	 */
	public String[] getLabels() {
		return labels;
	}
}
