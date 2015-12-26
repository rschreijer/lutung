package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.model.MandrillHelperClasses.EmailClass;
import com.microtripit.mandrillapp.lutung.view.MandrillWhitelistEntry;

import java.io.IOException;

public class MandrillWhitelistsApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillWhitelistsApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}
	
	/**
	 * <p>Adds an email to your email rejection whitelist. If the 
	 * address is currently on your blacklist, that blacklist 
	 * entry will be removed automatically.</p>
	 * @param email An email address to add to the whitelist.
	 * @return If the operation succeeded.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public Boolean add(final String email) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("whitelists/add.json")
                .addParam("email", email)
                .execute(WhitelistsAddResponse.class).getWhether();
		
	}
	
	/**
	 * <p>Retrieves your email rejection whitelist. You can provide 
	 * an email address or search prefix to limit the results. 
	 * Returns up to 1000 results.</p>
	 * @param email An optional email address or prefix to search by.
	 * @return Information for each whitelist entry.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillWhitelistEntry[] list(final String email) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("whitelists/list.json")
                .addParam("email", email)
                .execute(MandrillWhitelistEntry[].class);
	}
	
	/**
	 * <p>Removes an email address from the whitelist.</p>
	 * @param email The email address to remove from the whitelist.
	 * @return Whether the address was deleted successfully.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public Boolean delete(final String email) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
                .path("whitelists/delete.json")
                .addParam("email", email)
                .execute(WhitelistsDeleteResponse.class).getDeleted();
	}
	
	public static class WhitelistsAddResponse extends EmailClass {
		private Boolean whether;
		public Boolean getWhether() {
			return whether;
		}
	}
	
	public static class WhitelistsDeleteResponse {
		private Boolean deleted;
		public Boolean getDeleted() {
			return deleted;
		}
	}

}
