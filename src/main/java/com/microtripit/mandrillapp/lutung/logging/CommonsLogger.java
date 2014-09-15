package com.microtripit.mandrillapp.lutung.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author aldenquimby@gmail.com
 */
public class CommonsLogger implements Logger {

    private final Log log;

    public CommonsLogger(Class clazz) {
        log = LogFactory.getLog(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public void debug(final String msg) {
        if(log.isDebugEnabled()) {
            log.debug(msg);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public void error(final String msg) {
        this.error(msg, null);
    }

    @Override
    public void error(final String msg, final Throwable t) {
        if(log.isErrorEnabled()) {
            log.error(msg, t);
        }
    }
}
