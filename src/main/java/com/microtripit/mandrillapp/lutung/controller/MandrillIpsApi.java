/**
 * 
 */
package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillDedicatedIp;
import com.microtripit.mandrillapp.lutung.view.MandrillDedicatedIp.MandrillDnsCheck;
import com.microtripit.mandrillapp.lutung.view.MandrillDedicatedIpPool;

import java.io.IOException;
import java.util.Date;

/**
 * @author rschreijer
 *
 */
public class MandrillIpsApi {
	private final QueryExecutorFactory queryExecutorFactory;

	public MandrillIpsApi(final QueryExecutorFactory queryExecutorFactory) {
		this.queryExecutorFactory = Preconditions.checkNotNull(queryExecutorFactory, "queryExecutorFactory is null");
	}
	
	/**
	 * <p>Lists your dedicated IPs.</p>
	 * @return An array of dedicated IPs.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDedicatedIp[] list() 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/list.json")
				.execute(MandrillDedicatedIp[].class);
	}
	
	/**
	 * <p>Retrieves information about a single dedicated ip.</p>
	 * @param ip A dedicated IP address.
	 * @return Information about the dedicated ip.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDedicatedIp info(final String ip) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/info.json")
				.addParam("ip", ip)
				.execute(MandrillDedicatedIp.class);
	}
	
	/**
	 * <p>Requests an additional dedicated IP for your account. 
	 * Accounts may have one outstanding request at any time, 
	 * and provisioning requests are processed within 24 hours.</p>
	 * @param warmup Whether to enable warmup mode for the ip.
	 * @param pool The id of the pool to add the dedicated ip to, 
	 * or null to use your account's default pool.
	 * @return The date and time that the request was created.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public Date provision(final Boolean warmup, final String pool) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/provision.json")
				.addParam("warmup", warmup)
				.addParam("pool", pool)
				.execute(DateWrapper.class).getRequestedAt();
	}
	
	/**
	 * <p>Begins the warmup process for a dedicated IP. During the 
	 * warmup process, Mandrill will gradually increase the percentage 
	 * of your mail that is sent over the warming-up IP, over a period 
	 * of roughly 30 days. The rest of your mail will be sent over 
	 * shared IPs or other dedicated IPs in the same pool.</p>
	 * @param ip A dedicated ip address.
	 * @return Information about the dedicated IP.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDedicatedIp startWarmup(final String ip) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/start-warmup.json")
				.addParam("ip", ip)
				.execute(MandrillDedicatedIp.class);
	}
	
	/**
	 * <p>Cancels the warmup process for a dedicated IP.</p>
	 * @param ip A dedicated ip address.
	 * @return Information about the dedicated IP.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDedicatedIp cancelWarmup(final String ip) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/cancel-warmup.json")
				.addParam("ip", ip)
				.execute(MandrillDedicatedIp.class);
	}
	
	/**
	 * <p>Moves a dedicated IP to a different pool.</p>
	 * @param ip A dedicated ip address.
	 * @param pool The name of the new pool to add the dedicated ip to.
	 * @param createPool Whether to create the pool if it does not exist; 
	 * if false and the pool does not exist, an Unknown_Pool will be thrown.
	 * @return Information about the updated state of the dedicated IP.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDedicatedIp setPool(final String ip, 
			final String pool, final Boolean createPool) 
					throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/set-pool.json")
				.addParam("ip", ip)
				.addParam("pool", pool)
				.addParam("create_pool", createPool)
				.execute(MandrillDedicatedIp.class);
	}
	
	/**
	 * <p>Deletes a dedicated IP. This is permanent and cannot be undone.</p>
	 * @param ip The dedicated ip to remove from your account.
	 * @return A boolean indicating whether the ip was successfully deleted.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public Boolean delete(final String ip) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/delete.json")
				.addParam("ip", ip)
				.execute(DeleteResponse.class).getDeleted();
	}
	
	/**
	 * <p>Lists your dedicated IP pools.</p>
	 * @return The dedicated IP pools for your account, 
	 * up to a maximum of 1000.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDedicatedIpPool[] listPools() 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/list-pools.json")
				.execute(MandrillDedicatedIpPool[].class);
	}
	
	/**
	 * <p>Describes a single dedicated IP pool.</p>
	 * @param pool A pool name.
	 * @return Information about the dedicated ip pool.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDedicatedIpPool poolInfo(final String pool) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/pool-info.json")
				.addParam("pool", pool)
				.execute(MandrillDedicatedIpPool.class);
	}
	
	/**
	 * <p>Creates a pool and returns it. If a pool already exists 
	 * with this name, no action will be performed.</p>
	 * @param pool The name of a pool to create.
	 * @return Information about the dedicated ip pool.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDedicatedIpPool createPool(final String pool) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/create-pool.json")
				.addParam("pool", pool)
				.execute(MandrillDedicatedIpPool.class);
	}
	
	/**
	 * <p>Deletes a pool. A pool must be empty before you can delete 
	 * it, and you cannot delete your default pool.</p>
	 * @param pool The name of the pool to delete.
	 * @return Whether the pool was deleted.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public Boolean deletePool(final String pool) 
			throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/delete-pool.json")
				.addParam("pool", pool)
				.execute(DeletePoolResponse.class).getDeleted();
	}
	
	/**
	 * <p>Tests whether a domain name is valid for use as the 
	 * custom reverse DNS for a dedicated IP.</p>
	 * @param ip A dedicated ip address.
	 * @param domain The domain name to test.
	 * @return Validation results for the domain.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDnsCheck checkCustomDns(final String ip, 
			final String domain) throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/check-custom-dns.json")
				.addParam("ip", ip)
				.addParam("domain", domain)
				.execute(MandrillDnsCheck.class);
	}
	
	/**
	 * <p>Configures the custom DNS name for a dedicated IP.</p>
	 * @param ip A dedicated ip address.
	 * @param domain A domain name to set as the dedicated 
	 * IP's custom dns name.
	 * @return Information about the dedicated IP's new configuration.
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillDedicatedIp setCustomDns(final String ip, 
			final String domain) throws MandrillApiError, IOException {
		return queryExecutorFactory.create()
				.path("ips/set-custom-dns.json")
				.addParam("ip", ip)
				.addParam("domain", domain)
				.execute(MandrillDedicatedIp.class);
	}
	
	public static class DateWrapper {
		private Date requested_at;
		
		public Date getRequestedAt() {
			return requested_at;
		}
	}
	
	public static class DeleteResponse {
		private String ip;
		private Boolean deleted;
		
		public String getIp() {
			return ip;
		}
		public Boolean getDeleted() {
			return deleted;
		}
	}
	
	public static class DeletePoolResponse {
		private String pool;
		private Boolean deleted;
		
		public String getPool() {
			return pool;
		}
		public Boolean getDeleted() {
			return deleted;
		}
	}
	
}
