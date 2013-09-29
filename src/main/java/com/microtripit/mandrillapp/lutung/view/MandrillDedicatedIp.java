/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

import java.util.Date;

/**
 * <p>Info about a dedicated IP.</p>
 * @author rschreijer
 *
 */
public class MandrillDedicatedIp {
	private String ip, pool, domain;
	private Date created_at;
	private CustomDns custom_dns;
	private WarmupStatus warmup;
	
	/**
	 * @return The IP address.
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @return The name of the pool that this dedicated IP belongs to.
	 */
	public final String getPool() {
		return pool;
	}
	/**
	 * @return The domain name (reverse dns) of this dedicated IP.
	 */
	public final String getDomain() {
		return domain;
	}
	/**
	 * @return The date and time that the dedicated IP was created.
	 */
	public final Date getCreatedAt() {
		return created_at;
	}
	/**
	 * @return Information about the ip's custom dns, 
	 * if it has been configured.
	 */
	public final CustomDns getCustomDns() {
		return custom_dns;
	}
	/**
	 * @return Information about the ip's warmup status.
	 */
	public final WarmupStatus getWarmup() {
		return warmup;
	}

	/**
	 * <p>Information about an IP's custom dns.</p>
	 * @author rschreijer
	 *
	 */
	public static class CustomDns {
		private Boolean enabled, valid;
		private String error;
		
		/**
		 * @return A boolean indicating whether custom dns 
		 * has been configured for this ip.
		 */
		public Boolean getEnabled() {
			return enabled;
		}
		/**
		 * @return Whether the ip's custom dns is currently valid.
		 */
		public Boolean getValid() {
			return valid;
		}
		/**
		 * @return If the ip's custom dns is invalid, this will 
		 * include details about the error.
		 */
		public String getError() {
			return error;
		}
	}
	
	/**
	 * <p>Information about the ip's warmup status.</p>
	 * @author rschreijer
	 *
	 */
	public static class WarmupStatus {
		private Boolean warming_up;
		private Date start_at, end_at;
		
		/**
		 * @return Whether the ip is currently in warmup mode.
		 */
		public Boolean getWarmingUp() {
			return warming_up;
		}
		/**
		 * @return The start time for the warmup process.
		 */
		public Date getStartAt() {
			return start_at;
		}
		/**
		 * @return The end date and time for the warmup process.
		 */
		public Date getEndAt() {
			return end_at;
		}
	}
	
	/**
	 * <p>Validation results for a domain.</p>
	 * @author rschreijer
	 *
	 */
	public static class MandrillDnsCheck {
		private Boolean valid;
		private String error;
		
		/**
		 * @return Whether the domain name has a correctly-configured 
		 * A record pointing to the ip address.
		 */
		public Boolean getValid() {
			return valid;
		}
		/**
		 * @return If valid is false, this will contain details 
		 * about why the domain's A record is incorrect.
		 */
		public String getError() {
			return error;
		}
	}

}
