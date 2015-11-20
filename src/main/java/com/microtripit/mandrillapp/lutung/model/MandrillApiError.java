/**
 * 
 */
package com.microtripit.mandrillapp.lutung.model;

import com.google.gson.Gson;

/**
 * @author rschreijer
 * @since Mar 17, 2013
 */
public class MandrillApiError extends Exception {
	private static final long serialVersionUID = 1L;
	private MandrillError error;
	
	public MandrillApiError() { super(); }
	public MandrillApiError(final String msg) { super(msg); }
	public MandrillApiError(final Throwable t) { super(t); }
	public MandrillApiError(final String msg, final Throwable t) { 
		super(msg,t);
	}
	
	/**
	 * @return The error status returned by the Mandrill API.
	 */
	public final String getMandrillErrorStatus() {
		return error.getStatus();
	}
	/**
	 * @return <code>true</code> if this exception has a 
	 * Mandrill API error status; <code>false</code> otherwise.
	 */
	public final boolean hasMandrillErrorStatus() {
		return (error != null && error.hasStatus());
	}
	/**
	 * @return The name of the error as returned by the Mandrill API.
	 */
	public final String getMandrillErrorName() {
		return error.getName();
	}
	/**
	 * @return <code>true</code> if this exception has a 
	 * Mandrill API error name; <code>false</code> otherwise.
	 */
	public final boolean hasMandrillErrorName() {
		return (error != null && error.hasName());
	}
	/**
	 * @return The message of the error as returned by the Mandrill API.
	 */
	public final String getMandrillErrorMessage() {
		return error.getMessage();
	}
	/**
	 * @return <code>true</code> if this exception has a 
	 * Mandrill API error message; <code>false</code> otherwise.
	 */
	public final boolean hasMandrillErrorMessage() {
		return (error != null && error.hasMessage());
	}
	/**
	 * @return The error code of the error as returned by the Mnadrill API.
	 */
	public final Integer getMandrillErrorCode() {
		return error.getCode();
	}
	/**
	 * @return <code>true</code> if this exception has a 
	 * Mandrill API error code; <code>false</code> otherwise.
	 */
	public final boolean hasMandrillErrorCode() {
		return (error != null && error.hasCode());
	}
	
	/**
	 * <p>Generate a JSON string representation for the error 
	 * contained in this {@link MandrillApiError}.</p>
	 * @return The error as returned by the Mandrill API, in 
	 * JSON representation.
	 */
	public final String getMandrillErrorAsJson() {
		if(error != null) {
			final Gson gson = LutungGsonUtils.createGsonBuilder()
					.setPrettyPrinting().create();
			return gson.toJson(error);
			
		} else {
			return "{}";
			
		}
	}
	
	protected final MandrillApiError withError(final MandrillError error) {
		this.error = error;
		return this;
	}
	
	public static final class MandrillError {
		private String status, name, message;
		private Integer code;
		
		public MandrillError(String status, String name, String message, Integer code) {
            this.status = status;
            this.name = name;
            this.message = message;
            this.code = code;
        }

		/**
		 * @return The error status returned by the Mandrill API.
		 */
		public final String getStatus() {
			return status;
		}
		/**
		 * @return <code>true</code> if this exception has a 
		 * Mandrill API error status; <code>false</code> otherwise.
		 */
		public final boolean hasStatus() {
			return (status != null && !status.isEmpty());
		}
		/**
		 * @return The name of the error as returned by the Mandrill API.
		 */
		public final String getName() {
			return name;
		}
		/**
		 * @return <code>true</code> if this exception has a 
		 * Mandrill API error name; <code>false</code> otherwise.
		 */
		public final boolean hasName() {
			return (name != null && !name.isEmpty());
		}
		/**
		 * @return The error message
		 */
		public final String getMessage() {
			return message;
		}
		public final boolean hasMessage() {
			return (message != null && !message.isEmpty());
		}
		/**
		 * @return The error code of the error as returned by the Mnadrill API.
		 */
		public final Integer getCode() {
			return code;
		}
		/**
		 * @return <code>true</code> if this exception has a 
		 * Mandrill API error code; <code>false</code> otherwise.
		 */
		public final boolean hasCode() {
			return code != null;
		}

	}
	
}
