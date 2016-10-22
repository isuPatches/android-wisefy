package com.metova.wisefy.util;


import android.util.Log;
import com.metova.wisefy.BuildConfig;


public class LogUtil {

    private static final LogUtil LOG_UTIL = new LogUtil();

    /**
     * Private constructor with no setup
     */
    private LogUtil() {
    }

    /**
     * @return instance of LogUtil
     */
    public static LogUtil getInstance() {
        return LOG_UTIL;
    }

    public void d (String tag, String message, boolean loggingEnabled) {
        if(isLoggable(tag, Log.DEBUG, loggingEnabled)) {
            Log.d(tag, message);
        }
    }

    public void w (String tag, String message, boolean loggingEnabled) {
        if(isLoggable(tag, Log.WARN, loggingEnabled)) {
            Log.w(tag, message);
        }
    }

    public void e (String tag, String message, boolean loggingEnabled) {
        if(isLoggable(tag, Log.ERROR, loggingEnabled)) {
            Log.e(tag, message);
        }
    }

    private boolean isLoggable(String tag, int level, boolean loggingEnabled) {
        boolean isLoggable;
        if(BuildConfig.DEBUG || loggingEnabled) {
            isLoggable = true;
        } else {
            if (tag.length() > 23) {
                tag = tag.substring(0, 22);
            }
            isLoggable = Log.isLoggable(tag, level);
        }
        return isLoggable;
    }
}