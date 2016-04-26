package com.metova.wisefy.util;


import android.util.Log;
import com.metova.wisefy.BuildConfig;


public class LogUtil {

    public static void d (String tag, String message) {
        if(LogUtil.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, message);
        }
    }

    public static void d (String tag, String message, Throwable throwable) {
        if(LogUtil.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, message, throwable);
        }
    }

    public static void w (String tag, String message) {
        if(LogUtil.isLoggable(tag, Log.WARN)) {
            Log.w(tag, message);
        }
    }

    public static void w (String tag, String message, Throwable throwable) {
        if(LogUtil.isLoggable(tag, Log.WARN)) {
            Log.w(tag, message, throwable);
        }
    }

    public static void e (String tag, String message) {
        if(LogUtil.isLoggable(tag, Log.ERROR)) {
            Log.e(tag, message);
        }
    }

    public static void e (String tag, String message, Throwable throwable) {
        if(LogUtil.isLoggable(tag, Log.ERROR)) {
            Log.e(tag, message, throwable);
        }
    }

    public static boolean isLoggable(String tag, int level) {
        boolean isLoggable;
        if(BuildConfig.DEBUG) {
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