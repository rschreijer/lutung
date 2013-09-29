/**
 * 
 */
package com.microtripit.mandrillapp.lutung;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillApiTest extends MandrillTestCase {
	
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
	public final void testWhitelists() {
		Assert.assertNotNull(mandrillApi.whitelists());
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
	public final void testSubaccounts() {
		Assert.assertNotNull(mandrillApi.subaccounts());
	}
	
	@Test
	public final void testInbound() {
		Assert.assertNotNull(mandrillApi.inbound());
	}
	
	@Test
	public final void testExports() {
		Assert.assertNotNull(mandrillApi.exports());
	}
	
	@Test
	public final void testIps() {
		Assert.assertNotNull(mandrillApi.ips());
	}
}
