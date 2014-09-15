package com.microtripit.mandrillapp.lutung.logging;

/**
 * @author aldenquimby@gmail.com
 */
public class NoOpLogger implements Logger {

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public void debug(String msg) {
        // no-op
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public void error(String msg) {
        // no-op
    }

    @Override
    public void error(String msg, Throwable t) {
        // no op
    }
}
