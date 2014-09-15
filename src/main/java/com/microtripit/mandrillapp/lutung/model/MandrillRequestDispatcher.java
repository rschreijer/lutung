/**
 * 
 */
package com.microtripit.mandrillapp.lutung.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError.MandrillError;

/**
 * @author rschreijer
 * @since Feb 21, 2013
 */
public final class MandrillRequestDispatcher {
	private static final Log log = LogFactory.getLog(MandrillRequestDispatcher.class);
	
	public static final <T> T execute(final RequestModel<T> requestModel, 
			HttpClient client) throws MandrillApiError, IOException {

		HttpResponse response = null;
		InputStream responseInputStream = null;
		try {
			if(client == null) {
				log.debug("Using new instance of default http client");
				client = new DefaultHttpClient();
				client.getParams().setParameter(
						CoreProtocolPNames.USER_AGENT, 
						client.getParams().getParameter(CoreProtocolPNames.USER_AGENT)+ "/Lutung-0.1");
				String proxyHost = (String) requestModel.getRequestParams().get("http.proxyHost");
				String proxyPort = (String) requestModel.getRequestParams().get("http.proxyPort");
				if(proxyHost != null && proxyPort != null){
					HttpHost proxy = new HttpHost(proxyHost,Integer.parseInt(proxyPort));
					client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
				}
			}
			if(log.isDebugEnabled()) {
				log.debug("starting request '" +requestModel.getUrl()+ "'");
			}
			response = client.execute( requestModel.getRequest() );
			final StatusLine status = response.getStatusLine();
			responseInputStream = response.getEntity().getContent();
			if( requestModel.validateResponseStatus(status.getStatusCode()) ) {
				try {
					return requestModel.handleResponse( responseInputStream );
					
				} catch(final HandleResponseException e) {
					throw new IOException(
							"Failed to parse response from request '" 
							+requestModel.getUrl()+ "'", e);
					
				}
				
			} else {
				// ==> compile mandrill error!
				final String e = IOUtils.toString(responseInputStream);
				final MandrillError error = LutungGsonUtils.getGson()
						.fromJson(e, MandrillError.class);
				throw new MandrillApiError(
						"Unexpected http status in response: " 
						+status.getStatusCode()+ " (" 
						+status.getReasonPhrase()+ ")").withError(error);
				
			}
				
		} finally {
			if(responseInputStream != null) {
				responseInputStream.close();
			}
			if(response != null) {
				EntityUtils.consume(response.getEntity());
			}
		}
	}

}
