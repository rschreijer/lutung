/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.MandrillApiTest;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;
import com.microtripit.mandrillapp.lutung.view.MandrillUrl;

/**
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillUrlsApiTest {
	private static MandrillApi mandrillApi;
	
	@BeforeClass
	public static final void runBeforeClass() {
		final String key = MandrillApiTest.getMandrillApiKey();
		if(key != null) {
			mandrillApi = new MandrillApi(key);
		} else {
			mandrillApi = null;
		}
	}
	
	@Before
	public final void runBefore() {
		Assume.assumeNotNull(mandrillApi);
	}
	
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
