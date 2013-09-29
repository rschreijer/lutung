/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillTestCase;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillTag;
import com.microtripit.mandrillapp.lutung.view.MandrillTimeSeries;

/**
 * <p>Tests for the tags api implementations.</p>
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillTagsApiTest extends MandrillTestCase {
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		final MandrillTag[] tags = mandrillApi.tags().list();
		Assert.assertNotNull(tags);
		for(MandrillTag tag : tags) {
			Assert.assertNotNull(tag.getTag());
			Assert.assertNull(tag.getStats());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mandrillApi.tags().delete(null);
		Assert.fail();
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testInfo01() throws IOException, MandrillApiError {
		mandrillApi.tags().info(null);
		Assert.fail();
	}
	
	@Test
	public final void testInfo02() throws IOException, MandrillApiError {
		final MandrillTag[] tags = mandrillApi.tags().list();
		Assert.assertNotNull(tags);
		if(tags.length > 0) {
			final MandrillTag tag = mandrillApi.tags().info(tags[0].getTag());
			Assert.assertNotNull(tag.getTag());
			Assert.assertNotNull(tag.getStats());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testTimeSeries01() throws IOException, MandrillApiError {
		mandrillApi.tags().timeSeries(null);
		Assert.fail();
	}
	
	@Test
	public final void testTimeSeries02() throws IOException, MandrillApiError {
		final MandrillTag[] tags = mandrillApi.tags().list();
		Assert.assertNotNull(tags);
		if(tags.length > 0) {
			final MandrillTimeSeries[] series = mandrillApi.tags().timeSeries(
					tags[0].getTag());
			Assert.assertNotNull(series);
			for(MandrillTimeSeries s : series) {
				Assert.assertNotNull(s.getTime());
			}
		}
	}
	
	@Test
	public final void testAllTimeSeries() throws IOException, MandrillApiError {
		final MandrillTimeSeries[] series = mandrillApi.tags().allTimeSeries();
		Assert.assertNotNull(series);
		for(MandrillTimeSeries s : series) {
			Assert.assertNotNull(s.getTime());
		}
	}

}
