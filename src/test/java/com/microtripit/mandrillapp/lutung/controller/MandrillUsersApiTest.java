/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillTestCase;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillSender;
import com.microtripit.mandrillapp.lutung.view.MandrillUserInfo;

/**
 * <p>Tests for the users api implementations.</p>
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillUsersApiTest extends MandrillTestCase {
	
	@Test
	public final void testInfo() throws IOException, MandrillApiError {
		final MandrillUserInfo userInfo = mandrillApi.users().info();
		Assert.assertNotNull(userInfo);
		Assert.assertNotNull(userInfo.getPublicId());
		Assert.assertNotNull(userInfo.getUsername());
		Assert.assertNotNull(userInfo.getCreatedAt());
		Assert.assertTrue(userInfo.getReputation() >= 0);
		Assert.assertTrue(userInfo.getReputation() <= 100);
		Assert.assertTrue(userInfo.getHourlyQuota() >= 0);
		Assert.assertNotNull(userInfo.getStats());
	}
	
	@Test
	public final void testPing() throws IOException, MandrillApiError {
		final String pong = mandrillApi.users().ping();
		Assert.assertEquals("PONG!", pong);
	}
	
	@Test
	public final void testSenders() throws IOException, MandrillApiError {
		final MandrillSender[] senders = mandrillApi.users().senders();
		Assert.assertNotNull(senders);
		if(senders.length > 0) {
			Assert.assertNotNull(senders[0].getAddress());
			Assert.assertNotNull(senders[0].getCreatedAt());
		}
	}

}
