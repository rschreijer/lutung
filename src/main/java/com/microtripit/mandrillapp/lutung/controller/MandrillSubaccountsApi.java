package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillSubaccountInfo;

import java.io.IOException;

public class MandrillSubaccountsApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillSubaccountsApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}
	
	/**
	 * <p>Get the list of subaccounts defined for the account, 
	 * optionally filtered by a prefix.</p>
	 * @param q An optional prefix to filter the 
	 * subaccounts' ids and names.
	 * @return The subaccounts for the account, up to a maximum of 1000.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSubaccountInfo[] list(final String q) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("subaccounts/list.json")
				.addParam("q", q)
				.execute(MandrillSubaccountInfo[].class);
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
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSubaccountInfo add(final String id, final String name, 
			final String notes, final Integer customQuota)  
					throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("subaccounts/add.json")
				.addParam("id", id)
				.addParam("name", name)
				.addParam("notes", notes)
				.addParam("custom_quota", customQuota)
				.execute(MandrillSubaccountInfo.class);
	}
	
	/**
	 * <p>Given the ID of an existing subaccount, return the data about it.</p>
	 * @param id The unique identifier of the subaccount to query.
	 * @return The information about the subaccount.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSubaccountInfo info(final String id) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("subaccounts/info.json")
				.addParam("id", id)
				.execute(MandrillSubaccountInfo.class);
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
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSubaccountInfo update(final String id, 
			final String name, final String notes, final Integer customQuota)  
					throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("subaccounts/update.json")
				.addParam("id", id)
				.addParam("name", name)
				.addParam("notes", notes)
				.addParam("customQuota", customQuota)
				.execute(MandrillSubaccountInfo.class);
	}
	
	/**
	 * <p>Delete an existing subaccount. Any email related to the 
	 * subaccount will be saved, but stats will be removed and any 
	 * future sending calls to this subaccount will fail.</p>
	 * @param id The unique identifier of the subaccount.
	 * @return The information about the subaccount.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSubaccountInfo delete(final String id) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("subaccounts/delete.json")
				.addParam("id", id)
				.execute(MandrillSubaccountInfo.class);
	}
	
	/**
	 * <p>Pause a subaccount's sending. Any future emails delivered 
	 * to this subaccount will be queued for a maximum of 3 days 
	 * until the subaccount is resumed.</p>
	 * @param id The unique identifier of the subaccount to pause.
	 * @return The information for the paused subaccount.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSubaccountInfo pause(final String id) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("subaccounts/pause.json")
                .addParam("id", id)
                .execute(MandrillSubaccountInfo.class);
	}
	
	/**
	 * <p>Resume a paused subaccount's sending.</p>
	 * @param id The unique identifier of the subaccount to resume.
	 * @return The information for the resumed subaccount.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillSubaccountInfo resume(final String id) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("subaccounts/resume.json")
                .addParam("id", id)
                .execute(MandrillSubaccountInfo.class);
	}
}
