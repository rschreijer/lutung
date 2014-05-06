package com.microtripit.mandrillapp.lutung.logging;

/**
 * @author aldenquimby@gmail.com
 */
public class NoOpLogger implements Logger {

    @Override
    public void debug(String msg) {
        // no-op
    }
}
