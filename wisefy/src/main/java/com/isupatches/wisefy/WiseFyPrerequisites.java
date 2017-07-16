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
package com.isupatches.wisefy;


import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.isupatches.wisefy.util.LogUtil;


class WiseFyPrerequisites {

    private static final String TAG = WiseFyPrerequisites.class.getSimpleName();

    private static final WiseFyPrerequisites WISEFY_PREREQUISITES = new WiseFyPrerequisites();

    private WiseFyConfiguration mWiseFyConfiguration;

    private ConnectivityManager mConnectivityManager;

    private  WifiManager mWifiManager;

    /**
     * Private constructor with no setup
     */
    private WiseFyPrerequisites() {
        mWiseFyConfiguration = WiseFyConfiguration.getInstance();
    }

    /**
     * @return instance of WiseFyPrerequisites
     */
    static WiseFyPrerequisites getInstance() {
        return WISEFY_PREREQUISITES;
    }

    /**
    * Used internally to verify if the WiseFy instances has the necessary criteria to work
    *
    * @return boolean - True if all prerequisites are met
    */
    boolean hasPrerequisites() {
        if (mWifiManager == null) {
            if (LogUtil.isLoggable(TAG, Log.ERROR, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.e(TAG, "Missing WifiManager");
            }
            return false;
        }
        if (mConnectivityManager == null) {
            if (LogUtil.isLoggable(TAG, Log.ERROR, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.e(TAG, "Missing ConnectivityManager");
            }
            return false;
        }
        return true;
    }

    ConnectivityManager getConnectivityManager() {
        return mConnectivityManager;
    }

    WifiManager getWifiManager() {
        return mWifiManager;
    }

    void setConnectivityManager(ConnectivityManager mConnectivityManager) {
        this.mConnectivityManager = mConnectivityManager;
    }

    void setWifiManager(WifiManager mWifiManager) {
        this.mWifiManager = mWifiManager;
    }
}
