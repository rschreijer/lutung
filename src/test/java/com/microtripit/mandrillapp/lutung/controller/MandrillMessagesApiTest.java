/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillTestCase;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageInfo;
import com.microtripit.mandrillapp.lutung.view.MandrillSearchMessageParams;

/**
 * <p>Tests for the messages api implementations.</p>
 * @author rschreijer
 * @since Mar 21, 2013
 */
public final class MandrillMessagesApiTest extends MandrillTestCase {
	
	@Test(expected=MandrillApiError.class)
	public final void testSend01() throws IOException, MandrillApiError {
		mandrillApi.messages().send(null, null);
		Assert.fail();
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testSendTemplate01() throws IOException, MandrillApiError {
		final HashMap<String,String> templateContent = 
				new HashMap<String,String>();
		templateContent.put("test", "value");
		final MandrillMessage message = new MandrillMessage();
		mandrillApi.messages().sendTemplate(null, templateContent, message, null);
		Assert.fail();
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testSendTemplate02() throws IOException, MandrillApiError {
		final HashMap<String,String> templateContent = 
				new HashMap<String,String>();
		templateContent.put("test", "value");
		mandrillApi.messages().sendTemplate("bvy38q34v93vzn39u4bvu9ewvbi349", 
				templateContent, null, null);
		Assert.fail();
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testSendTemplate03() throws IOException, MandrillApiError {
		final MandrillMessage message = new MandrillMessage();
		mandrillApi.messages().sendTemplate("bvy38q34v93vzn39u4bvu9ewvbi349", 
				null, message, null);
		Assert.fail();
	}
	
	@Test
	public final void testSearch01() throws IOException, MandrillApiError {
		Assert.assertNotNull(mandrillApi.messages().search(null));
	}
	
	@Test
	public final void testSearch02() throws IOException, MandrillApiError {
		final MandrillSearchMessageParams params = 
				new MandrillSearchMessageParams();
		params.setQuery("com");
		final MandrillMessageInfo[] info = mandrillApi.messages().search(params);
		Assert.assertNotNull(info);
		for(MandrillMessageInfo i : info) {
			Assert.assertNotNull(i.getId());
			Assert.assertNotNull(i.getSender());
		}
	}
}
