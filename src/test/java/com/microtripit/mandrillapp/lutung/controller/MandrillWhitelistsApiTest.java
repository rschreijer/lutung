package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillTestCase;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillWhitelistEntry;

public final class MandrillWhitelistsApiTest extends MandrillTestCase {
	
	@Test(expected=MandrillApiError.class)
	public final void testAdd() throws IOException, MandrillApiError {
		mandrillApi.whitelists().add(null);
		Assert.fail();
	}
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		final MandrillWhitelistEntry[] entries = 
				mandrillApi.whitelists().list(null);
		Assert.assertNotNull(entries);
		for(MandrillWhitelistEntry entry : entries) {
			Assert.assertNotNull(entry.getEmail());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mandrillApi.whitelists().delete(null);
		Assert.fail();
	}
	
}
