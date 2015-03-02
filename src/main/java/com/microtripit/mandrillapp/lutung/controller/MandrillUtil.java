/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.model.MandrillRequest;
import com.microtripit.mandrillapp.lutung.model.MandrillRequestDispatcher;
import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
final class MandrillUtil {
	protected static final String rootUrl = "https://mandrillapp.com/api/1.0/";
	
	/**
	 * @param key
	 * @return
	 */
	protected static  HashMap<String,Object> paramsWithKey(final String key) {
		final HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("key",key);
		return params;

	}
	
	protected static <OUT> OUT query(final String url,
			final Map<String,Object> params, Class<OUT> responseType)
					throws MandrillApiError, IOException {

        return query(url, params, responseType, null);
	}

    protected static <OUT> OUT query(final String url,
            final Map<String,Object> params, Class<OUT> responseType, HttpClient httpClient)
            throws MandrillApiError, IOException {

        final MandrillRequest<OUT> requestModel =
                new MandrillRequest<OUT>(url, params, responseType);
        return MandrillRequestDispatcher.execute(requestModel, httpClient);
    }
}
