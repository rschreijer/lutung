/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.HashMap;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.model.MandrillHelperClasses.MandrillRejectsDeleted;
import com.microtripit.mandrillapp.lutung.view.MandrillRejectsEntry;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public final class MandrillRejectsApi {
	private static final String rootUrl = MandrillUtil.rootUrl;
	private final String key;
	
	public MandrillRejectsApi(final String key) {
		this.key = key;
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
	public final MandrillRejectsEntry[] list(final String email, 
			final Boolean includeExpired) throws MandrillApiError, 
			IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("email", email);
		params.put("include_expired", includeExpired);
		return MandrillUtil.query(rootUrl+ "rejects/list.json", 
				params, MandrillRejectsEntry[].class);
		
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
	public final Boolean delete(final String email) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("email", email);
		return MandrillUtil.query(rootUrl+ "rejects/delete.json", 
				params, MandrillRejectsDeleted.class).getDeleted();
		
	}

}
