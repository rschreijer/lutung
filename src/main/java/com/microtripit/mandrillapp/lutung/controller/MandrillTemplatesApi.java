/**
 *
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.model.MandrillContentWrapper;
import com.microtripit.mandrillapp.lutung.model.MandrillHelperClasses.MandrillRenderTemplateResponse;
import com.microtripit.mandrillapp.lutung.view.MandrillTemplate;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillTemplatesApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillTemplatesApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}

	/**
	 * <p>Add a new template.</p>
	 * @param name The name for the new template - must be unique.
	 * @param code The HTML code for the template with mc:edit
	 * attributes for the editable elements.
	 * @param publish Set to false to add a draft template
	 * without publishing.
	 * @return The information saved about the new template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate add(final String name,
			final String code, final Boolean publish)
					throws MandrillApiError, IOException {

		return add(name, null, null, null, code, null, publish, null);

	}

	/**
	 * <p>Add a new template.</p>
	 * @param name The name for the new template - must be unique.
	 * @param code The HTML code for the template with mc:edit
	 * attributes for the editable elements.
	 * @param publish Set to false to add a draft template
	 * without publishing.
	 * @return The information saved about the new template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate add(final String name,
			final String code, final Boolean publish, String[] labels)
					throws MandrillApiError, IOException {

		return add(name, null, null, null, code, null, publish, labels);

	}

	/**
	 * <p>Add a new template.</p>
	 * @param name The name for the new template - must be unique.
	 * @param fromEmail A default sending address for emails
	 * sent using this template.
	 * @param fromName A default from name to be used.
	 * @param subject A default subject line to be used.
	 * @param code The HTML code for the template with mc:edit
	 * attributes for the editable elements.
	 * @param text A default text part to be used when
	 * sending with this template.
	 * @param publish Set to false to add a draft template
	 * without publishing.
	 * @param labels Strings associated with the template
	 * @return The information saved about the new template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate add(final String name, final String fromEmail,
			final String fromName, final String subject, final String code,
			final String text, final Boolean publish, String[] labels)
					throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("templates/add.json")
				.addParam("name", name)
				.addParam("from_email", fromEmail)
				.addParam("from_name", fromName)
				.addParam("subject", subject)
				.addParam("code", code)
				.addParam("text", text)
				.addParam("publish", publish)
				.addParam("labels", labels)
				.execute(MandrillTemplate.class);
	}

	/**
	 * <p>Get the information for an existing template.</p>
	 * @param name The immutable name of an existing template.
	 * @return The information saved about the template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate info(final String name)
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("templates/info.json")
				.addParam("name", name)
				.execute(MandrillTemplate.class);
	}

	/**
	 * <p>Update the code for an existing template.</p>
	 * @param name The immutable name of an existing template.
	 * @param code The new code for the template.
	 * @param publish Set to false to update the draft
	 * version of the template without publishing.
	 * @return The updated template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate update(final String name,
			final String code, final Boolean publish)
					throws MandrillApiError, IOException {

		return update(name, null, null, null, code, null, publish, null);

	}

	/**
	 * <p>Update the code for an existing template.</p>
	 * @param name The immutable name of an existing template.
	 * @param code The new code for the template.
	 * @param publish Set to false to update the draft
	 * version of the template without publishing.
	 * @param labels Strings associated with the template
	 * @return The updated template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate update(final String name,
			final String code, final Boolean publish, String[] labels)
					throws MandrillApiError, IOException {

		return update(name, null, null, null, code, null, publish, labels);

	}

	/**
	 * <p>Update the code for an existing template.</p>
	 * @param name The immutable name of an existing template.
	 * @param fromEmail A default sending address for emails
	 * sent using this template.
	 * @param fromName A default from name to be used.
	 * @param subject A default subject line to be used.
	 * @param code The new code for the template.
	 * @param text A default text part to be used when
	 * sending with this template.
	 * @param publish Set to false to update the draft
	 * version of the template without publishing.
	 * @param labels Strings associated with the template
	 * @return The updated template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate update(final String name,
			final String fromEmail, final String fromName,
			final String subject, final String code, final String text,
			final Boolean publish, String[] labels) throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("templates/update.json")
				.addParam("name", name)
				.addParam("from_email", fromEmail)
				.addParam("from_name", fromName)
				.addParam("subject", subject)
				.addParam("code", code)
				.addParam("text", text)
				.addParam("publish", publish)
				.addParam("labels", labels)
				.execute(MandrillTemplate.class);
	}

	/**
	 * <p>Publish the content for the template. Any new messages
	 * sent using this template will start using the content
	 * that was previously in draft.</p>
	 * @param name The immutable name of an existing template.
	 * @return The published template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate publish(final String name)
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("templates/publish.json")
				.addParam("name", name)
				.execute(MandrillTemplate.class);
	}

	/**
	 * <p>Delete a template.</p>
	 * @param name The immutable name of an existing template.
	 * @return The deleted template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate delete(final String name)
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("templates/delete.json")
				.addParam("name", name)
				.execute(MandrillTemplate.class);
	}

	/**
	 * <p>Get a list of all the templates available to this user.</p>
	 * @return An array of {@link MandrillTemplate} objects with
	 * information about each template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate[] list()
			throws MandrillApiError, IOException {
        return queryExecutorFactory.create()
                .path("templates/list.json")
                .execute(MandrillTemplate[].class);
	}

	/**
	 * <p>Get a list of all the templates available to this user.</p>
	 * @param label String associated with the template
	 * @return An array of {@link MandrillTemplate} objects with
	 * information about each template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTemplate[] list(String label)
			throws MandrillApiError, IOException {
        return queryExecutorFactory.create()
                .path("templates/list.json")
                .addParam("label", label)
                .execute(MandrillTemplate[].class);
	}

	/**
	 * <p>Get the recent history (hourly stats for the last
	 * 30 days) for a template.</p>
	 * @param name The immutable name of an existing template.
	 * @return An array of {@link MandrillTimeSeries} objects
	 * containing stats from the history of this template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillTimeSeries[] timeSeries(final String name)
			throws MandrillApiError, IOException {
        return queryExecutorFactory.create()
                .path("templates/time-series.json")
                .addParam("name", name)
                .execute(MandrillTimeSeries[].class);
	}

	/**
	 * <p>Inject content and optionally merge fields into
	 * a template, returning the HTML that results.</p>
	 * @param name The immutable name of an existing template.
	 * @param templateContent A map of template content to render.
	 * Each item in the map is used as key (name): the name of the
	 * content block to set the content for, and value (content):
	 * the actual content to put into the block.
	 * @param mergeVars Optional merge variables to use for injecting
	 * merge field content. If this is not provided, no merge fields
	 * will be replaced.
	 * @return The result of rendering the given template with the
	 * content and merge field values injected.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public String render(final String name,
			final Map<String,String> templateContent,
			final Map<String,String> mergeVars)
					throws MandrillApiError, IOException {
        return queryExecutorFactory.create()
                .path("templates/render.json")
                .addParam("template_name", name)
                .delegate(new Consumer<QueryExecutor>() {
                    @Override
                    public void accept(final QueryExecutor queryExecutor) {
                        addContent(templateContent, queryExecutor);
                    }
                })
                .delegate(new Consumer<QueryExecutor>() {
                    @Override
                    public void accept(final QueryExecutor queryExecutor) {
                        addContent(mergeVars, queryExecutor);
                    }
                })
                .execute(MandrillRenderTemplateResponse.class).getHtml();
	}

    private void addContent(final Map<String, String> contentItems, final QueryExecutor queryExecutor) {
        if(contentItems != null && !contentItems.isEmpty()) {
			final ArrayList<MandrillContentWrapper> contents =
					new ArrayList<MandrillContentWrapper>(contentItems.size());
			for(String cName : contentItems.keySet()) {
				contents.add( MandrillContentWrapper.create(
						cName, contentItems.get(cName)) );
			}
            queryExecutor.addParam("template_content", contents);
		}
    }
}
