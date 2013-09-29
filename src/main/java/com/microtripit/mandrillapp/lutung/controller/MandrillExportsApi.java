/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillExportJobInfo;

/**
 * <p>Exports Calls.</p>
 * @author rschreijer
 *
 */
public final class MandrillExportsApi {
	private static final String rootUrl = MandrillUtil.rootUrl;
	private final String key;
	
	public MandrillExportsApi(final String key) {
		this.key = key;
	}
	
	/**
	 * <p>Returns information about an export job. If the export 
	 * job's state is 'complete', the returned data will include 
	 * a URL you can use to fetch the results. Every export job 
	 * produces a zip archive, but the format of the archive is 
	 * distinct for each job type. The api calls that initiate 
	 * exports include more details about the output format for 
	 * that job type.</p>
	 * @param id An export job identifier.
	 * @return The information about the export.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillExportJobInfo info(final String id) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("id", id);
		return MandrillUtil.query(rootUrl+ "exports/info.json", 
				params, MandrillExportJobInfo.class);
		
	}
	
	/**
	 * <p>Returns a list of your exports.</p>
	 * @return The account's exports.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillExportJobInfo list() 
			throws MandrillApiError, IOException {
		
		return MandrillUtil.query(rootUrl+ "exports/list.json", 
				MandrillUtil.paramsWithKey(key),
				MandrillExportJobInfo.class);
		
	}
	
	/**
	 * <p>Begins an export of your rejection blacklist. The blacklist 
	 * will be exported to a zip archive containing a single file named 
	 * rejects.csv that includes the following fields:
	 * <ul><li>email</li>
	 * <li>reason</li>
	 * <li>detail</li>
	 * <li>created_at</li>
	 * <li>expires_at</li>
	 * <li>last_event_at</li>
	 * <li>expires_at.</li></ul></p>
	 * @param notifyEmail An optional email address to notify when 
	 * the export job has finished.
	 * @return Information about the rejects export job that was started.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillExportJobInfo rejects(final String notifyEmail) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("notify_email", notifyEmail);
		return MandrillUtil.query(rootUrl+ "exports/rejects.json", 
				params, MandrillExportJobInfo.class);
		
	}
	
	/**
	 * <p>Begins an export of your rejection whitelist. The whitelist 
	 * will be exported to a zip archive containing a single file named 
	 * whitelist.csv that includes the following fields:
	 * <ul><li>email</li>
	 * <li>detail</li>
	 * <li>created_at</li></ul></p>
	 * @param notifyEmail An optional email address to notify when 
	 * the export job has finished.
	 * @return Information about the whitelist export job that was started.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillExportJobInfo whitelist(final String notifyEmail) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("notify_email", notifyEmail);
		return MandrillUtil.query(rootUrl+ "exports/whitelist.json", 
				params, MandrillExportJobInfo.class);
		
	}
	
	/**
	 * <p>Begins an export of your activity history. The activity will 
	 * be exported to a zip archive containing a single file named 
	 * activity.csv in the same format as you would be able to export 
	 * from your account's activity view. It includes the following fields:
	 * <ul><li>date</li>
	 * <li>email</li>
	 * <li>sender</li>
	 * <li>subject</li>
	 * <li>status</li>
	 * <li>tags</li>
	 * <li>opens</li>
	 * <li>clicks</li>
	 * <li>bounce detail</li></ul>
	 * If you have configured any custom metadata fields, they will 
	 * be included in the exported data.</p>
	 * @param notifyEmail An optional email address to notify when 
	 * the export job has finished.
	 * @param dateFrom Start date.
	 * @param dateTo End date.
	 * @param tags An array of tag names to narrow the export to; 
	 * will match messages that contain ANY of the tags.
	 * @param senders An array of senders to narrow the export to.
	 * @param states An array of states to narrow the export to; 
	 * messages with ANY of the states will be included.
	 * @param apiKeys An array of api keys to narrow the export to; 
	 * messsagse sent with ANY of the keys will be included.
	 * @return Information about the activity export job that was started.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final MandrillExportJobInfo activity(final String notifyEmail, 
			final Date dateFrom, final Date dateTo, 
			final Collection<String> tags, final Collection<String> senders,
			final Collection<String> states, final Collection<String> apiKeys) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = MandrillUtil.paramsWithKey(key);
		params.put("notify_email", notifyEmail);
		params.put("date_from", dateFrom);
		params.put("date_to", dateTo);
		params.put("tags", tags);
		params.put("senders", senders);
		params.put("states", states);
		params.put("api_keys", apiKeys);
		return MandrillUtil.query(rootUrl+ "exports/activity.json", 
				params, MandrillExportJobInfo.class);
		
	}

}
