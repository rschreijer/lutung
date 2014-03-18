/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.model.MandrillRequest;
import com.microtripit.mandrillapp.lutung.model.MandrillRequestDispatcher;

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
	protected static final HashMap<String,Object> paramsWithKey(final String key) {
		final HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("key",key);
		List l = null;
		try {
		    l = ProxySelector.getDefault().select(new URI(rootUrl));
		} 
		catch (URISyntaxException e) {
		    e.printStackTrace();
		}
		if (l != null) {
		    for (Iterator iter = l.iterator(); iter.hasNext();) {
		    	java.net.Proxy proxy = (java.net.Proxy) iter.next();

		    	InetSocketAddress addr = (InetSocketAddress) proxy.address();

		    	if (addr != null){
		    		params.put("http.proxyHost",addr.getHostName());
		    		params.put("http.proxyPort", Integer.toString(addr.getPort()));
		    	}
		    }
		}
		return params;
	}
	
	/**
	 * @param url
	 * @param params
	 * @param responseType
	 * @return
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	protected static final <OUT> OUT query(final String url, 
			final Map<String,Object> params, Class<OUT> responseType) 
					throws MandrillApiError, IOException {
		
		final MandrillRequest<OUT> requestModel = 
				new MandrillRequest<OUT>(url, params, responseType);
		return MandrillRequestDispatcher.execute(requestModel, null);
		
	}
}
