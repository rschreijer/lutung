/**
 * 
 */
package com.microtripit.mandrillapp.lutung.http;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;

import java.io.IOException;
import java.util.Map;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillService {
	private final String rootUrl;
	private final Dispatcher dispatcher;
	private final ImmutableMap<String, Object> baseKeyMap;

	public MandrillService(final String rootUrl, final Dispatcher dispatcher, final String key) {
		this.rootUrl = Preconditions.checkNotNull(rootUrl, "rootUrl is null");
		this.dispatcher = Preconditions.checkNotNull(dispatcher,"dispatcher is null");
        baseKeyMap = ImmutableMap.<String, Object>builder().put("key", key).build();
	}

	/**
	 * @param path
	 * @param params
	 * @param responseType
	 * @return
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public final <OUT> OUT query(final String path, final Map<String,Object> params, final Class<OUT> responseType)
			throws MandrillApiError, IOException {
        final Map<String, Object> paramsWithKey = Maps.newHashMap(baseKeyMap);
        final String url = rootUrl + path;

        paramsWithKey.putAll(params);
        return dispatcher.send(url, paramsWithKey, responseType);
	}
}
