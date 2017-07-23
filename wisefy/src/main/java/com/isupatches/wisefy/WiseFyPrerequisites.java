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
import com.isupatches.wisefy.annotations.Internal;
import com.isupatches.wisefy.util.LogUtil;


/**
 * A class used internally as a bridge for other parts of the library to use a shared set of
 * prerequisite instances.  This contains the WifiManager and ConnectivityManger used throughout
 * the library.
 *
 * @author Patches
 */
@Internal
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
     * @see WiseFyConfiguration#isLoggingEnabled()
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

    /**
     * Used internally to return a shared ConnectivityManager instance to the rest of the library
     *
     * @return ConnectivityManager|null - The shared ConnectivityManager instance if any or null
     */
    ConnectivityManager getConnectivityManager() {
        return mConnectivityManager;
    }

    /**
     * Used internally to return a shared WifiManager instance to the rest of the library
     *
     * @return WifiManager|null - The shared WifiManager instance if any or null
     */
    WifiManager getWifiManager() {
        return mWifiManager;
    }


    /**
     * Used internally to set the shared ConnectivityManager instance for the rest of the library
     *
     * *NOTE* Called upon initialization or in test
     */
    void setConnectivityManager(ConnectivityManager mConnectivityManager) {
        this.mConnectivityManager = mConnectivityManager;
    }

    /**
     * Used internally to set the shared WifiManager instance for the rest of the library
     *
     * *NOTE* Called upon initialization or in test
     */
    void setWifiManager(WifiManager mWifiManager) {
        this.mWifiManager = mWifiManager;
    }
}
