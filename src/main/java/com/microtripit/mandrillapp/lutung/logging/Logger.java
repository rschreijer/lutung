package com.microtripit.mandrillapp.lutung.logging;

/**
 * @author aldenquimby@gmail.com
 */
public interface Logger {

    /**
     * Is debug logging currently enabled?
     */
    boolean isDebugEnabled();

    /**
     * Log a debug message if enabled.
     */
    void debug(String msg);

    /**
     * Is error logging currently enabled?
     */
    boolean isErrorEnabled();

    /**
     * Log an error message if enabled.
     */
    void error(String msg);

    /**
     * Log an error message if enabled.
     */
    void error(String msg, Throwable t);


}
