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

/**
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillRejectsApiTest {
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
		Assert.assertNotNull( mandrillApi.rejects().list(null, null) );
		
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mandrillApi.rejects().delete(null);
		Assert.fail();
	}

}
