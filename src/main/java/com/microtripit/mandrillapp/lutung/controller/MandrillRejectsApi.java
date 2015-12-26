/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.model.MandrillHelperClasses.MandrillRejectsAdded;
import com.microtripit.mandrillapp.lutung.model.MandrillHelperClasses.MandrillRejectsDeleted;
import com.microtripit.mandrillapp.lutung.view.MandrillRejectsEntry;

import java.io.IOException;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillRejectsApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillRejectsApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}
	
	public Boolean add(final String email, final String comment, 
			final String subaccount) throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("rejects/add.json")
                .addParam("email", email)
                .addParam("comment", comment)
                .addParam("subaccount", subaccount)
                .execute(MandrillRejectsAdded.class).getAdded();
	}
	
	/**
	 * <p>Retrieve your email rejection blacklist. You can 
	 * provide an email address to limit the results. Returns 
	 * up to 1000 results. By default, entries that have expired 
	 * are excluded from the results; use includeExpired to 
	 * true to include them.</p>
	 * @param email An optional email address to search by.
	 * @param includeExpired Whether to include rejections that 
	 * have already expired.
	 * @return Up to 1000 {@link MandrillRejectsEntry} objects.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillRejectsEntry[] list(final String email, 
			final Boolean includeExpired) throws MandrillApiError, IOException {
	
		return list(email, includeExpired, null);
		
	}
	
	/**
	 * <p>Retrieve your email rejection blacklist. You can 
	 * provide an email address to limit the results. Returns 
	 * up to 1000 results. By default, entries that have expired 
	 * are excluded from the results; use includeExpired to 
	 * true to include them.</p>
	 * @param email An optional email address to search by.
	 * @param includeExpired Whether to include rejections that 
	 * have already expired.
	 * @param subaccount An optional unique identifier for the 
	 * subaccount to limit the blacklist.
	 * @return Up to 1000 {@link MandrillRejectsEntry} objects.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillRejectsEntry[] list(final String email, 
			final Boolean includeExpired, final String subaccount) 
					throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("rejects/list.json")
                .addParam("email", email)
                .addParam("include_expired", includeExpired)
                .addParamIfNotNull("subaccount", subaccount)
                .execute(MandrillRejectsEntry[].class);
	}
	
	/**
	 * <p>Delete an email rejection. There is no limit to 
	 * how many rejections you can remove from your blacklist, 
	 * but keep in mind that each deletion has an affect on 
	 * your reputation.</p>
	 * @param email The email address that was removed from 
	 * the blacklist.
	 * @return Whether the address was deleted successfully.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public Boolean delete(final String email) 
			throws MandrillApiError, IOException {
		
		return delete(email, null);
		
	}
	
	/**
	 * <p>Delete an email rejection. There is no limit to 
	 * how many rejections you can remove from your blacklist, 
	 * but keep in mind that each deletion has an affect on 
	 * your reputation.</p>
	 * @param email The email address that was removed from 
	 * the blacklist.
	 * @param subaccount An optional unique identifier for the 
	 * subaccount to limit the blacklist.
	 * @return Whether the address was deleted successfully.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public Boolean delete(final String email, final String subaccount) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("rejects/delete.json")
                .addParam("email", email)
                .addParamIfNotNull("subaccount", subaccount)
                .execute(MandrillRejectsDeleted.class).getDeleted();
	}
	
}
