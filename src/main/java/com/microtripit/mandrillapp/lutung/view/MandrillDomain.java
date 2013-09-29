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
	private Date created_at, last_tested_at, verified_at;
	private MandrillDomainDetails spf, dkim;
	private Boolean valid_signing;
	
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
	
	/**
	 * @return When the domain's DNS settings were last tested.
	 */
	public Date getLastTestedAt() {
		return last_tested_at;
	}
	
	/**
	 * @return If the domain has been verified, this indicates 
	 * when that verification occurred.
	 */
	public Date getVerifiedAt() {
		return verified_at;
	}
	
	/**
	 * @return Details about the domain's SPF record.
	 */
	public MandrillDomainDetails getSpf() {
		return spf;
	}
	
	/**
	 * @return Details about the domain's DKIM record.
	 */
	public MandrillDomainDetails getDkim() {
		return dkim;
	}
	
	/**
	 * @return Whether this domain can be used to authenticate mail, 
	 * either for itself or as a custom signing domain. If this is 
	 * false but spf and dkim are both valid, you will need to 
	 * verify the domain before using it to authenticate mail.
	 */
	public Boolean getValidSigning() {
		return valid_signing;
	}
	
	public static class MandrillDomainDetails {
		private Boolean valid;
		private Date valid_after;
		private String error;
		
		/**
		 * @return Whether the domain's SPF record is 
		 * valid for use with Mandrill.
		 */
		public final Boolean getValid() {
			return valid;
		}
		/**
		 * @return When the domain's SPF record will be 
		 * considered valid for use with Mandrill. If set, 
		 * this indicates that the record is valid now, but 
		 * was previously invalid, and Mandrill will wait 
		 * until the record's TTL elapses to start using it.
		 */
		public final Date getValid_after() {
			return valid_after;
		}
		/**
		 * @return An error describing the spf record, or 
		 * null if the record is correct.
		 */
		public final String getError() {
			return error;
		}
	}
	
	/**
	 * Information about a verification that was sent.
	 * @author rschreijer
	 *
	 */
	public class MandrillDomainVerificationInfo {
		private String status, domain, email;

		/**
		 * @return 'sent' indicates that the verification has been sent, 
		 * 'already_verified' indicates that the domain has already 
		 * been verified with your account.
		 */
		public final String getStatus() {
			return status;
		}

		/**
		 * @return The domain name you provided.
		 */
		public final String getDomain() {
			return domain;
		}

		/**
		 * @return The email address the verification email was sent to.
		 */
		public final String getEmail() {
			return email;
		}
		
	}

}
