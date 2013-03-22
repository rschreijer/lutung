/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

import java.util.Date;

/**
 * <p>Information on a sending domain for the account.</p>
 * @author rschreijer
 * @since Mar 18, 2013
 */
public class MandrillDomain {
	private String domain;
	private Date created_at;
	
	/**
	 * @return The sender domain name.
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * @return The date and time that the sending domain 
	 * was first seen, UTC.
	 */
	public Date getCreatedAt() {
		return created_at;
	}
	
	

}
