/**
 * 
 */
package com.microtripit.mandrillapp.lutung.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

/**
 * @author rschreijer
 * @since Mar 16, 2013
 */
public class MandrillRequest<OUT> implements RequestModel<OUT> {
	private static final Log log = LogFactory.getLog(MandrillRequest.class);
	
	private final String url;
	private final Class<OUT> responseContentType;
	private final Map<String,? extends Object> requestParams;
	
	public MandrillRequest( final String url, 
			final Map<String,? extends Object> params, 
			final Class<OUT> responseType ) {
		
		if(responseType == null) {
			throw new NullPointerException();
			
		}
		this.url = url;
		this.requestParams = params;
		this.responseContentType = responseType;
	}

	public final String getUrl() {
		return url;
	}

	public final HttpRequestBase getRequest() throws IOException {
		final String paramsStr = LutungGsonUtils.getGson().toJson(
				requestParams, requestParams.getClass());
		if(log.isDebugEnabled()) {
			log.debug("raw content for request:\n" +paramsStr);
		}
		final StringEntity entity = new StringEntity(paramsStr, "UTF-8");
		entity.setContentType("application/json");
		final HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		return request;
		
	}

	public final boolean validateResponseStatus(final int httpResponseStatus) {
		return (httpResponseStatus == 200);
	}

	public final OUT handleResponse(final InputStream is) 
			throws HandleResponseException {
		
		try {
			final String raw = IOUtils.toString(is);
			if(log.isDebugEnabled()) {
				log.debug("raw content from response:\n" +raw);
			}
			return LutungGsonUtils.getGson().fromJson(
					raw, responseContentType);
			
		} catch(final Throwable t) {
			throw new HandleResponseException(t);
			
		}
	}

}
