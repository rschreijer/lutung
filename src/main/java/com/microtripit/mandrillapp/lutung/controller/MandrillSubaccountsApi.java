package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.HashMap;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillSubaccountInfo;

public class MandrillSubaccountsApi {
	private final String key;
	private final String rootUrl;

	public MandrillSubaccountsApi(final String key, final String url) {
		this.key = key;
		this.rootUrl = url;
	}
	
	public MandrillSubaccountsApi(final String key) {
		this(key, MandrillApi.rootUrl);
	}
	
	/**
	 * <p>Get the list of subaccounts defined for the account, 
	 * optionally filtered by a prefix.</p>
	 * @param q An optional prefix to filter the 
	 * subaccounts' ids and names.
	 * @return The subaccounts for the account, up to a maximum of 1000.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillSubaccountInfo[] list(final String q) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("q", q);
		return MandrillUtil.query(rootUrl+ "subaccounts/list.json", 
				params, MandrillSubaccountInfo[].class);
		
	}
	
	/**
	 * <p>Add a new subaccount.</p>
	 * @param id A unique identifier for the subaccount 
	 * to be used in sending calls.
	 * @param name An optional display name to further 
	 * identify the subaccount.
	 * @param notes Optional extra text to associate 
	 * with the subaccount.
	 * @param customQuota An optional manual hourly quota 
	 * for the subaccount. If not specified, Mandrill will 
	 * manage this based on reputation.
	 * @return The information saved about the new subaccount.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillSubaccountInfo add(final String id, final String name, 
			final String notes, final Integer customQuota)  
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		params.put("name", name);
		params.put("notes", notes);
		params.put("custom_quota", customQuota);
		return MandrillUtil.query(rootUrl+ "subaccounts/add.json", 
				params, MandrillSubaccountInfo.class);
		
	}
	
	/**
	 * <p>Given the ID of an existing subaccount, return the data about it.</p>
	 * @param id The unique identifier of the subaccount to query.
	 * @return The information about the subaccount.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillSubaccountInfo info(final String id) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		return MandrillUtil.query(rootUrl+ "subaccounts/info.json", 
				params, MandrillSubaccountInfo.class);
		
	}
	
	/**
	 * <p>Update an existing subaccount.</p>
	 * @param id A unique identifier for the subaccount 
	 * to be used.
	 * @param name An optional display name to further 
	 * identify the subaccount.
	 * @param notes Optional extra text to associate 
	 * with the subaccount.
	 * @param customQuota An optional manual hourly quota 
	 * for the subaccount. If not specified, Mandrill will 
	 * manage this based on reputation.
	 * @return The information for the updated subaccount.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillSubaccountInfo update(final String id, 
			final String name, final String notes, final Integer customQuota)  
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		params.put("name", name);
		params.put("notes", notes);
		params.put("customQuota", customQuota);
		return MandrillUtil.query(rootUrl+ "subaccounts/update.json", 
				params, MandrillSubaccountInfo.class);
		
	}
	
	/**
	 * <p>Delete an existing subaccount. Any email related to the 
	 * subaccount will be saved, but stats will be removed and any 
	 * future sending calls to this subaccount will fail.</p>
	 * @param id The unique identifier of the subaccount.
	 * @return The information about the subaccount.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillSubaccountInfo delete(final String id) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		return MandrillUtil.query(rootUrl+ "subaccounts/delete.json", 
				params, MandrillSubaccountInfo.class);
		
	}
	
	/**
	 * <p>Pause a subaccount's sending. Any future emails delivered 
	 * to this subaccount will be queued for a maximum of 3 days 
	 * until the subaccount is resumed.</p>
	 * @param id The unique identifier of the subaccount to pause.
	 * @return The information for the paused subaccount.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillSubaccountInfo pause(final String id) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		return MandrillUtil.query(rootUrl+ "subaccounts/pause.json", 
				params, MandrillSubaccountInfo.class);
		
	}
	
	/**
	 * <p>Resume a paused subaccount's sending.</p>
	 * @param id The unique identifier of the subaccount to resume.
	 * @return The information for the resumed subaccount.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillSubaccountInfo resume(final String id) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		return MandrillUtil.query(rootUrl+ "subaccounts/resume.json", 
				params, MandrillSubaccountInfo.class);
		
	}

}
