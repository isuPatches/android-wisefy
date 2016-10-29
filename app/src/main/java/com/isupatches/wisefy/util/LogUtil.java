/**
 * Copyright 2016 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.wisefy.util;


import android.util.Log;


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
        if(loggingEnabled) {
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