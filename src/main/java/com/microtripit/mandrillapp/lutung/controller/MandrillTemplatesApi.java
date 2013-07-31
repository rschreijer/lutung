/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.model.MandrillContentWrapper;
import com.microtripit.mandrillapp.lutung.model.MandrillHelperClasses.MandrillRenderTemplateResponse;
import com.microtripit.mandrillapp.lutung.view.MandrillTemplate;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public final class MandrillTemplatesApi {
	private static final String rootUrl = MandrillUtil.rootUrl;
	private final String key;
	
	public MandrillTemplatesApi(final String key) {
		this.key = key;
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
	public final MandrillTemplate add(final String name, 
			final String code, final Boolean publish) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("name", name);
		params.put("code", code);
		params.put("publish", publish);
		return MandrillUtil.query(rootUrl+ "templates/add.json", 
				params, MandrillTemplate.class);
		
	}
	
	/**
	 * <p>Get the information for an existing template.</p>
	 * @param name The immutable name of an existing template.
	 * @return The information saved about the template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillTemplate info(final String name) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("name", name);
		return MandrillUtil.query(rootUrl+ "templates/info.json", 
				params, MandrillTemplate.class);
		
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
	public final MandrillTemplate update(final String name, 
			final String code, final Boolean publish) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("name", name);
		params.put("code", code);
		params.put("publish", publish);
		return MandrillUtil.query(rootUrl+ "templates/update.json", 
				params, MandrillTemplate.class);
		
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
	public final MandrillTemplate publish(final String name) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("name", name);
		return MandrillUtil.query(rootUrl+ "templates/publish.json", 
				params, MandrillTemplate.class);
		
	}
	
	/**
	 * <p>Delete a template.</p>
	 * @param name The immutable name of an existing template.
	 * @return The deleted template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillTemplate delete(final String name) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("name", name);
		return MandrillUtil.query(rootUrl+ "templates/delete.json", 
				params, MandrillTemplate.class);
		
	}
	
	/**
	 * <p>Get a list of all the templates available to this user.</p>
	 * @return An array of {@link MandrillTemplate} objects with 
	 * information about each template.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillTemplate[] list() 
			throws MandrillApiError, IOException {
		
		return MandrillUtil.query(
				rootUrl+ "templates/list.json", 
				MandrillUtil.paramsWithKey(key), 
				MandrillTemplate[].class);
		
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
	public final MandrillTimeSeries[] timeSeries(final String name) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("name", name);
		return MandrillUtil.query(rootUrl+ "templates/time-series.json", 
				params, MandrillTimeSeries[].class);
		
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
	public final String render(final String name, 
			final Map<String,String> templateContent, 
			final Map<String,String> mergeVars) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("template_name", name);
		if(templateContent != null && !templateContent.isEmpty()) {
			final ArrayList<MandrillContentWrapper> contents = 
					new ArrayList<MandrillContentWrapper>(templateContent.size());
			for(String cName : templateContent.keySet()) {
				contents.add( MandrillContentWrapper.create(
						cName, templateContent.get(cName)) );
			}
			params.put("template_content", contents);
		}
		if(mergeVars != null && !mergeVars.isEmpty()) {
			final ArrayList<MandrillContentWrapper> vars = 
					new ArrayList<MandrillContentWrapper>(mergeVars.size());
			for(String cName : mergeVars.keySet()) {
				vars.add( MandrillContentWrapper.create(
						cName, mergeVars.get(cName)) );
			}
			params.put("merge_vars", vars);
		}
		return MandrillUtil.query(rootUrl+ "templates/render.json", 
				params, MandrillRenderTemplateResponse.class).getHtml();
		
	}
}
