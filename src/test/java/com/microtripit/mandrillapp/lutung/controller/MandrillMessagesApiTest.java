/**
 *
 */
package com.microtripit.mandrillapp.lutung.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Assume;
import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillTestCase;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient.Type;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageContent;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageInfo;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.microtripit.mandrillapp.lutung.view.MandrillSearchMessageParams;
import com.microtripit.mandrillapp.lutung.view.MandrillTemplate;

import junit.framework.Assert;

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

	@Test
	public final void testSend02() throws IOException, MandrillApiError {
	    Recipient to = new Recipient();
	    to.setEmail("to@test.com");
	    to.setType(Type.TO);
	    List<Recipient> recipients = new ArrayList<MandrillMessage.Recipient>();
	    recipients.add(to);
	    MandrillMessage message = new MandrillMessage();
	    message.setFromEmail("from@test.com");
	    message.setTo(recipients);
        MandrillMessageStatus[] status = mandrillApi.messages().send(message, false);
        Assert.assertNotNull(status);
        Assert.assertTrue("sent".equals(status[0].getStatus()));
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
    public final void testSendTemplate04() throws IOException, MandrillApiError {
        String templateName = "lutung_templatename_unit_test_sendTemplate04_" + System.currentTimeMillis();

        final MandrillTemplate t = mandrillApi.templates().add(templateName,
                "<html><body><h1>Hello World!</h1></body></html>", false);
        Assert.assertNotNull(t);
        Assert.assertNotNull(t.getName());

        Recipient to = new Recipient();
        to.setEmail("to@test.com");
        to.setType(Type.TO);
        List<Recipient> recipients = new ArrayList<MandrillMessage.Recipient>();
        recipients.add(to);
        MandrillMessage message = new MandrillMessage();
        message.setFromEmail("from@test.com");
        message.setTo(recipients);
        MandrillMessageStatus[] status = mandrillApi.messages()
                                                    .sendTemplate(templateName,
                                                                  null,
                                                                  message,
                                                                  null);
        Assert.assertNotNull(status);

        Assert.assertNotNull( mandrillApi.templates().delete(t.getName()) );

    }

	@Test
	public final void testSearch01() throws IOException, MandrillApiError {
		Assert.assertNotNull(mandrillApi.messages().search(null));
	}

	@Test
	public final void testSearch02() throws IOException, MandrillApiError {
		final MandrillSearchMessageParams params =
				new MandrillSearchMessageParams();
		params.setQuery("email:test.com");
		final MandrillMessageInfo[] info = mandrillApi.messages().search(params);
		Assert.assertNotNull(info);
		for(MandrillMessageInfo i : info) {
			Assert.assertNotNull(i.getId());
			Assert.assertNotNull(i.getSender());
		}
	}

    @Test
    public void testContent01() throws Exception {
        // The content call only works on 'recently sent' messages.  So get a listing
        // of messages from the last 6 hours, and try to get their content.
        // This means that the testing account must have at least one sent message within
        // that time.
        Calendar cal = Calendar.getInstance();
        cal.add( Calendar.HOUR, -6 );
        MandrillSearchMessageParams search = new MandrillSearchMessageParams();
        search.setDateFrom( cal.getTime() );
        search.setDateTo( new Date() );
        MandrillMessageInfo[] messages = mandrillApi.messages().search( search );
        Assume.assumeNotNull( messages );
        Assume.assumeTrue( messages.length > 0 );

        for ( MandrillMessageInfo info : messages ) {
            MandrillMessageContent content = mandrillApi.messages().content( info.getId() );
            Assert.assertNotNull( content );
        }
    }

	@Test
	public void testParse01() throws IOException, MandrillApiError {
		String testUnparsedMsg = "From: sender@example.com\n" +
				"To: recipient.email@example.com\n" +
				"Subject: Lutang test subject\n\n" +
				"Sup mandrill !";
		MandrillMessage parsedMessage = mandrillApi.messages().parse(testUnparsedMsg);
		Assume.assumeNotNull(parsedMessage);
		Assert.assertEquals("Lutang test subject", parsedMessage.getSubject());
	}

	@Test(expected = MandrillApiError.class)
	public void testParse02() throws IOException, MandrillApiError {
		MandrillMessage parsedMessage = mandrillApi.messages().parse(null);
		Assert.fail();
	}
}
