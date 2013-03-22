/**
 * 
 */
package com.microtripit.mandrillapp.lutung;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillApiTest {
	private static final Log log = LogFactory.getLog(MandrillApiTest.class);
	
	/**
	 * <p>If you want to run your own tests, either provide a file
	 * 'myapikey.txt' on your classpath, or simply change this 
	 * method to return your hard-coded string.</p>
	 * <p>If you provide a file, this file should ONLY contain your
	 * Mandrill api key, as plain text in ONLY ONE line. This also 
	 * allows you to keep your api key secret since 'myapikey.txt'
	 * is mentioned in .gitignore and will not be pushed to git!</p> 
	 * @return Your Mandrill API key.
	 */
	public static final String getMandrillApiKey() {
		try {
			final InputStream is = MandrillApiTest.class.getClassLoader()
					.getResourceAsStream("myapikey.txt");
			if(is == null) {
				throw new FileNotFoundException(
						"Please change " +MandrillApiTest.class.getCanonicalName()
						+ ".getMandrillApiKey() to just return your Mandrill " +
						"api key. The file being loaded in that method is just " +
						"a security measure ... I didn't want my own api key in " +
						"a public git repo ;-)");
			}
			final String apikey = IOUtils.toString(is);
			is.close();
			return apikey;
			
		} catch(final IOException e) {
			log.error("No Mandrill API key defined - " +
					"please provide your Mandrill API key!", e);
			return null;
			
		}
	}
	
	/////////
	/*
	 * TESTS
	 */
	/////////
	private static MandrillApi mandrillApi;
	
	@BeforeClass
	public static final void runBeforeClass() {
		final String key = MandrillApiTest.getMandrillApiKey();
		if(key != null) {
			mandrillApi = new MandrillApi(MandrillApiTest.getMandrillApiKey());
		} else {
			mandrillApi = null;
		}
	}
	
	@Before
	public final void runBefore() {
		Assume.assumeNotNull(mandrillApi);
	}
	
	@Test
	public final void testApiKey() {
		final String key = mandrillApi.getKey();
		Assert.assertNotNull(key);
		Assert.assertFalse( key.isEmpty() );
		Assert.assertFalse( key.equals("<put ur Mandrill API key here>") );
	}
	
	@Test
	public final void testUsers() {
		Assert.assertNotNull(mandrillApi.users());
	}
	
	@Test
	public final void testMessages() {
		Assert.assertNotNull(mandrillApi.messages());
	}
	
	@Test
	public final void testTags() {
		Assert.assertNotNull(mandrillApi.tags());
	}
	
	@Test
	public final void testRejects() {
		Assert.assertNotNull(mandrillApi.rejects());
	}
	
	@Test
	public final void testSenders() {
		Assert.assertNotNull(mandrillApi.senders());
	}
	
	@Test
	public final void testUrls() {
		Assert.assertNotNull(mandrillApi.urls());
	}
	
	@Test
	public final void testTemplates() {
		Assert.assertNotNull(mandrillApi.templates());
	}
	
	@Test
	public final void testWebhooks() {
		Assert.assertNotNull(mandrillApi.webhooks());
	}
	
	@Test
	public final void testInbound() {
		Assert.assertNotNull(mandrillApi.inbound());
	}
}
