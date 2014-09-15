package com.microtripit.mandrillapp.lutung.util;

/**
 * @author aldenquimby@gmail.com
 */
public final class FeatureDetector {

    private static Boolean commonsLoggingAvailable;

    /**
     * Check if apache commons logging is available.
     *
     * @return {@code true} if available, {@code false} otherwise.
     */
    public static boolean isCommonsLoggingAvailable() {
        if (commonsLoggingAvailable == null) {
            commonsLoggingAvailable = isClassPresent("org.apache.commons.logging.Log");
        }
        return commonsLoggingAvailable;
    }

    /**
     * Check whether the {@link Class} identified by the supplied name is present.
     *
     * @param className the name of the class to check
     * @return {@code true} true if class is present, {@code false} otherwise
     */
    private static boolean isClassPresent(String className) {
        try {
            Class.forName(className);
            return true;
        }
        catch (Throwable ex) {
            return false;
        }
    }
}
