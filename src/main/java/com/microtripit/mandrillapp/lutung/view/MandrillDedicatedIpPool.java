/**
 * 
 */
package com.microtripit.mandrillapp.lutung.view;

import java.util.Date;
import java.util.List;

/**
 * @author rschreijer
 *
 */
public class MandrillDedicatedIpPool {
	private String name;
	private Date created_at;
	private List<MandrillDedicatedIp> ips;
	
	/**
	 * @return This pool's name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return The date and time that this pool was created.
	 */
	public Date getCreatedAt() {
		return created_at;
	}
	/**
	 * @return The dedicated IPs in this pool.
	 */
	public List<MandrillDedicatedIp> getIps() {
		return ips;
	}

}
