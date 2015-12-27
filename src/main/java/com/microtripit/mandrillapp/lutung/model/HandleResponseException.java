/**
 * 
 */
package com.microtripit.mandrillapp.lutung.model;

/**
 * @author rschreijer
 * @since Jan 8, 2013
 */
public final class HandleResponseException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public HandleResponseException(final String msg, final Throwable t) { super(msg,t); }

}
