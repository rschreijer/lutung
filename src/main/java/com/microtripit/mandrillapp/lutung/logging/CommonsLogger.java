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
    public void debug(String msg) {
        if (log.isDebugEnabled()) {
            log.debug(msg);
        }
    }
}
