/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillTestCase;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;
import com.microtripit.mandrillapp.lutung.view.MandrillUrl;

/**
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillUrlsApiTest extends MandrillTestCase {
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		final MandrillUrl[] urls = mandrillApi.urls().list();
		Assert.assertNotNull(urls);
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testSearch01() throws IOException, MandrillApiError {
		mandrillApi.urls().search(null);
		Assert.fail();
	}
	
	@Test
	public final void testSearch02() throws IOException, MandrillApiError {
		final MandrillUrl[] urls = mandrillApi.urls().search("com");
		Assert.assertNotNull(urls);
		for(MandrillUrl url : urls) {
			Assert.assertNotNull(url.getUrl());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testTimeSeries01() throws IOException, MandrillApiError {
		mandrillApi.urls().timeSeries(null);
		Assert.fail();
	}
	
	@Test
	public final void testTimeSeries02() throws IOException, MandrillApiError {
		final MandrillUrl[] urls = mandrillApi.urls().list();
		Assert.assertNotNull(urls);
		if(urls.length > 0 && urls[0].getUrl() != null) {
			final MandrillTimeSeries[] series = mandrillApi.urls()
					.timeSeries(urls[0].getUrl());
			Assert.assertNotNull(series);
			for(MandrillTimeSeries t : series) {
				Assert.assertNotNull(t.getTime());
			}
		}
	}

}
