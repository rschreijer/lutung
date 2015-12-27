/**
 * 
 */
package com.microtripit.mandrillapp.lutung.http;

import com.microtripit.mandrillapp.lutung.logging.Logger;
import com.microtripit.mandrillapp.lutung.logging.LoggerFactory;
import com.microtripit.mandrillapp.lutung.model.*;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError.MandrillError;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author rschreijer
 * @since Feb 21, 2013
 */
public final class MandrillRequestDispatcher implements Dispatcher {
    private static final Logger log = LoggerFactory.getLogger(MandrillRequestDispatcher.class);

	/**
	 * See https://hc.apache.org/httpcomponents-core-4.3.x/httpcore/apidocs/org/apache/http/params/HttpConnectionParams.html#setSoTimeout(org.apache.http.params.HttpParams, int)
	 *
	 * A value of 0 means no timeout at all.
	 * The value is expressed in milliseconds.
	 * */
	public static int SOCKET_TIMEOUT_MILLIS = 0;

	/**
	 * See https://hc.apache.org/httpcomponents-core-4.3.x/httpcore/apidocs/org/apache/http/params/HttpConnectionParams.html#setConnectionTimeout(org.apache.http.params.HttpParams, int)
	 *
	 * A value of 0 means no timeout at all.
	 * The value is expressed in milliseconds.
	 * */
	public static int CONNECTION_TIMEOUT_MILLIS = 0;

	public <V> V send(final String url, final Map<String,Object> params, Class<V> responseType) throws IOException, MandrillApiError {
		final MandrillRequest<V> requestModel = new MandrillRequest<V>(url, params, responseType);
		return execute(requestModel, null);
	}

	private <T> T execute(final RequestModel<T> requestModel,
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
                // use proxy?
                final ProxyData proxyData = detectProxyServer(requestModel.getUrl());
                if(proxyData != null) {
                    if(log.isDebugEnabled()) {
                        log.debug(String.format("Using proxy @" +proxyData.host+ ":"+String.valueOf(proxyData.port)));
                    }
                    final HttpHost proxy = new HttpHost(proxyData.host, proxyData.port);
                    client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
                }
				HttpConnectionParams.setSoTimeout(client.getParams(), SOCKET_TIMEOUT_MILLIS);
				HttpConnectionParams.setConnectionTimeout(client.getParams(), CONNECTION_TIMEOUT_MILLIS);
			}
            log.debug("starting request '" +requestModel.getUrl()+ "'");
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
				MandrillError error = null;
				try {
				    error = LutungGsonUtils.getGson()
						.fromJson(e, MandrillError.class);
				} catch (Throwable ex) {
				    error = new MandrillError("Invalid Error Format",
				                              "Invalid Error Format",
				                              e,
				                              status.getStatusCode());
				}

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
				consume(response.getEntity());
			}
		}
	}

    private ProxyData detectProxyServer(final String url) {
        try {
            final List<Proxy> proxies = ProxySelector.getDefault().select(new URI(url));
            if(proxies != null) {
                for(Proxy proxy : proxies) {
                    InetSocketAddress addr = (InetSocketAddress) proxy.address();
                    if(addr != null) {
                        return new ProxyData(addr.getHostName(), addr.getPort());
                    }
                }
            }
            // no proxy detected!
            return null;

        } catch (final Throwable t) {
            log.error("Error detecting proxy server", t);
            return null;

        }
    }

	private void consume(HttpEntity entity) throws IOException {
		if (entity == null) {
			return;
		}
		if (entity.isStreaming()) {
			InputStream inputStream = entity.getContent();
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

    private static final class ProxyData {
        String host;
        int port;

        protected ProxyData(final String host, final int port) {
            this.host = host;
            this.port = port;
        }

    }

}
