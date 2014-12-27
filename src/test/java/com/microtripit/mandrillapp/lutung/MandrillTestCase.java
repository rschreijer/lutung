/**
 * 
 */
package com.microtripit.mandrillapp.lutung;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;

import com.microtripit.mandrillapp.lutung.MandrillApi;

/**
 * @author rschreijer
 *
 */
public abstract class MandrillTestCase {
	private static final Log log = LogFactory.getLog(MandrillApiTest.class);
	
	protected static MandrillApi mandrillApi;
	
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
	protected static final String getMandrillApiKey() {
		try {
			final InputStream is = MandrillTestCase.class.getClassLoader()
					.getResourceAsStream("myapikey.txt");
			if(is == null) {
				throw new FileNotFoundException(
						"Please change " +MandrillTestCase.class.getCanonicalName()
						+ ".getMandrillApiKey() to just return your Mandrill " +
						"api key. The file being loaded in that method is just " +
						"a security measure ... I didn't want my own api key in " +
						"a public git repo ;-)");
			}
			final String apikey = IOUtils.toString(is);
			is.close();
			if(apikey == null || apikey.isEmpty()) {
				throw new IOException("Empty file 'myapikey.txt'");

			}
			return apikey;

		} catch(final IOException e) {
			log.error("No Mandrill API key defined - " +
					"please provide your Mandrill API key!", e);
			return null;

		}
	}
	
	@BeforeClass
	public static final void runBeforeClass() {
		final String key = getMandrillApiKey();
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

}
