/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillTestCase;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillDomain;
import com.microtripit.mandrillapp.lutung.view.MandrillSender;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;

/**
 * <p>Tests for the senders api implementations.</p>
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillSendersApiTest extends MandrillTestCase {
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		final MandrillSender[] senders = mandrillApi.senders().list();
		Assert.assertNotNull( senders );
		for(MandrillSender s : senders) {
			Assert.assertNotNull(s.getAddress());
		}
	}
	
	@Test
	public final void testDomains() throws IOException, MandrillApiError {
		final MandrillDomain[] domains = mandrillApi.senders().domains();
		Assert.assertNotNull(domains);
		for(MandrillDomain d : domains) {
			Assert.assertNotNull(d.getDomain());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testAddDomain() throws IOException, MandrillApiError {
		mandrillApi.senders().addDomain(null);
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testInfo01() throws IOException, MandrillApiError {
		mandrillApi.senders().info(null);
		Assert.fail();
	}
	
	@Test
	public final void testInfo02() throws IOException, MandrillApiError {
		final MandrillSender[] senders = mandrillApi.senders().list();
		Assert.assertNotNull( senders );
		if(senders.length > 0) {
			final MandrillSender s = mandrillApi.senders().info(
					senders[0].getAddress());
			Assert.assertNotNull(s);
			Assert.assertNotNull(s.getAddress());
			Assert.assertNotNull(s.getCreatedAt());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testTimeSeries01() throws MandrillApiError, IOException {
		mandrillApi.senders().timeSeries(null);
		Assert.fail();
	}
	
	@Test
	public final void testTimeSeries02() throws MandrillApiError, IOException {
		final MandrillSender[] senders = mandrillApi.senders().list();
		Assert.assertNotNull( senders );
		if(senders.length > 0) {
			final MandrillTimeSeries[] series = 
					mandrillApi.senders().timeSeries(senders[0].getAddress());
			Assert.assertNotNull(series);
			for(MandrillTimeSeries s : series) {
				Assert.assertNotNull(s.getTime());
			}
		}
	}

}
