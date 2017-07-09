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


import android.os.Build;


public class SSIDUtil {

    private static final SSIDUtil SSID_UTIL = new SSIDUtil();

    /**
     * Private constructor with no setup
     */
    private SSIDUtil() {
    }

    /**
     * @return instance of SSIDUtil
     */
    static SSIDUtil getInstance() {
        return SSID_UTIL;
    }

    String convertSSIDForConfig(String ssid) {
        // On devices with version Kitkat and below, We need to send SSID name
        // with double quotes. On devices with version Lollipop, We need to send
        // SSID name without double quotes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ssid;
        } else {
            return "\"" + ssid + "\"";
        }
    }
}
