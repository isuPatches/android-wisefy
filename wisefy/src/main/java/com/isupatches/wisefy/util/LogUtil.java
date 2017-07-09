/*
 * Copyright 2017 Patches Klinefelter
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


/**
 * Logging helper class
 */
public class LogUtil {

    /**
     * Checks to see given a TAG, log level, and if logging is enabled if logging should occur
     *
     * @param tag - The tag to be used for the log
     * @param level - The level of logging (i.e Log.DEBUG, Log.WARN, Log.ERROR, etc)
     * @param loggingEnabled - If the instances of WiseFy has logging set to always be enabled
     *
     * {@link Log}
     *
     * @return boolean - if logging is enabled
     */
    public static boolean isLoggable(String tag, int level, boolean loggingEnabled) {
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